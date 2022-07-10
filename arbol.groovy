import java.util.ArrayList

// Implementacion de un arbol binario que guarda valores tanto en ramas como en hojas
// Se crean a su vez las clases hoja y rama que conforman el arbol

// clase hoja que guarda un valor
// y a su vez guarda dos ramas que conectan la hoja con sus hijos
// si el valor de ambas ramas es null, entonces la hoja no tiene hijos
class Hoja {

    int valor
    Rama left, right

    Hoja(int valor) {
        this.valor = valor
        this.left = right = null
    }

}

// Clase rama que conecta una hoja con su hijo, guarda un valor y la hoja con la que conecta
class Rama {

    int valor
    Hoja hoja

    Rama(int valor) {
        this.valor = valor
        this.hoja = null
    }

}

// clase arbol que crea un arbol binario con valores en ramas y hojas
// tambien se crean varios metodos adicionales para determinar si es arbol es completo y si es un max heap
// asi como los metodos para obtener el recorrido pre-order y post-order
class Arbol {

    Hoja root

    Arbol() {
        root = null
    }

    // Metodo que obtiene la cantidad de hojas del arbol
    // recibe como entrada el nodo desde que el va a empezar a contar
    // y se llama recursivamente por cada uno de sus hijos
    int obtenerCantidadHojas(Hoja root){
        if (root == null){
            return 0;
        }

        if(root.left == null && root.right == null){
            return 1
        }else if (root.left != null && root.right == null){
            return (1 + obtenerCantidadHojas(root.left.hoja));    
        }else if (root.left == null && root.right != null){
            return (1 + obtenerCantidadHojas(root.right.hoja));    
        }else{
            return (1 + obtenerCantidadHojas(root.left.hoja) + obtenerCantidadHojas(root.right.hoja));
        }
    }

    // metodo para determinar si el arbol es completo
    // este metodo se utiliza para determinar si el arbol cumple con las propiedades de un heap
    // es decir, que sea completo y que todos los valores de cada su arbol son menores o mayores a los de su padre
    // recibe la hoja desde donde empezara a verificar, el index de donde se ubica esa hoja en el arbol
    // y el numero de nodos del arbol
    boolean esCompleto(Hoja root, int index,int cantidadNodos){
        
        if (root == null){
            return true;
        }
        // si el index del nodo es mayor que el numero de nodos, entonces el arbol no es completo
        if (index >= cantidadNodos){
            return false;
        }

        if (root.left == null && root.right == null){
            // caso el nodo no tiene hijos
            return true;
        }else if (root.left != null && root.right == null){
            // caso el nodo tiene un hijo izquierdo
            // si solo tiene un hijo izquierdo cumple con la definicion
            return true;
        }else if (root.left == null && root.right != null){
            // caso el nodo tiene un hijo derecho
            // si solo tiene un hijo derecho no cumple con la definicion
            return false;
        }else{
            return esCompleto(root.left.hoja,2 * index + 1,cantidadNodos) && esCompleto(root.right.hoja,2 * index + 2,cantidadNodos);
        }
        
    }
    
    // metodo auxiliar que determina si es el arbol es un max heap
    // por cada hoja se verifica que sus hijos y sus ramas tengan un valor menor que el suyo
    // y se llama nuevamente al metodo con cada uno de sus hijos
    // a diferencia de un arbol binario tradicional, como este tambien contiene valores en las ramas,
    // se toman en cuenta para definir si el arbol es o no un max heap
    boolean esMaxHeapAux(Hoja root){
        // si el nodo no tiene hijos se retornar true
        if (root.left == null && root.right == null){
            return true;
        }
 
        // si solo tiene un hijo izquierdo de verifica su valor
        // este caso solo ocurre con nodos del penultimo nivel
        if (root.right == null) {
            return root.valor >= root.left.hoja.valor;
        }
        else {
            
            if (root.valor >= root.left.hoja.valor && root.valor >= root.left.valor &&
                root.valor >= root.right.hoja.valor && root.valor >= root.right.valor){
                return esMaxHeapAux(root.left.hoja) && esMaxHeapAux(root.right.hoja);
            }else{
                return false;
            }
                
        }
    }

    // Metodo que devuelve si el arbol es un max heap
    // se evalua que se cumpla que el arbol sea completo y que todos los valores de cada hoja sean menores que los de su padre
    boolean esMaxHeap(Hoja root){
        if (root == null){
            return true;
        }
            
        int cantHojas = obtenerCantidadHojas(root);
 
        return esCompleto(root, 0, cantHojas)  && esMaxHeapAux(root)
    }

    // metodo que determina si el arbol es un max heap simetrico
    // para ello se verifica si se trata de un max heap y si sus recorridos pre y post order son iguales
    boolean esMaxHeapSimetrico(Hoja root){

        if (!esMaxHeap(root)){
            println("No es un Max Heap")
            return false
        }else if (esMaxHeap(root) && this.preorder(root) != this.postorder(root) ){
            println("Es Max Heap pero no es simetrico")
            return false
        }else{
            println("Es Max Heap simetrico")
            return true
        }
        
    }

    // metodo que devuelve una lista con los valores del recorrido pre-order del arbol
    // se recorre el arbol de izquierda a derecha y se agregan los valores de cada nodo y rama a una lista a medida que se pasa por ellos
    ArrayList<Integer> listaValoresPre = []
    ArrayList<Integer> preorder(Hoja hoja) {
        if (hoja == null) {
            return
        }
        
        // Se agrega el valor de la hoja actual y se verifica que tenga hijos
        // si tiene algun hijo, se agrega el valor de la rama correspondiente a la lista
        // y se llama el metodo recursivamente con la hoja hija de la actual
        listaValoresPre.add(hoja.valor)
        Rama ramaIzq = hoja.left
        if (!(ramaIzq == null)) {
            listaValoresPre.add(ramaIzq.valor)
            
            preorder(ramaIzq.hoja)
        }
        Rama ramaDer = hoja.right
        if (!(ramaDer == null)) {
            listaValoresPre.add(ramaDer.valor)
            
            preorder(ramaDer.hoja)
        }
        return listaValoresPre
    }

    // metodo que devuelve una lista con los valores del recorrido pre-order del arbol
    // se recorre el arbol de izquierda a derecha y se agregan los valores de cada nodo y rama desde el ultimo nivel del arbol hacia el primero
    ArrayList<Integer> listaValoresPost = []
    ArrayList<Integer> postorder(Hoja hoja) {
        if (hoja == null) {
            return
        }

        Rama ramaIzq = hoja.left
        if (!(ramaIzq == null)) {        
            postorder(ramaIzq.hoja)
    
            listaValoresPost.add(ramaIzq.valor)
        }
        Rama ramaDer = hoja.right
        if (!(ramaDer == null)) {            
            postorder(ramaDer.hoja)
        
            listaValoresPost.add(ramaDer.valor)
        }
        
        listaValoresPost.add(hoja.valor)
        return listaValoresPost
    }

}

Arbol Arbol = new Arbol()

Arbol.root = new Hoja(9)
Arbol.root.left = new Rama(11)
Arbol.root.left.hoja = new Hoja(5)
Arbol.root.right = new Rama(12)
Arbol.root.right.hoja = new Hoja(4)
Arbol.root.right.hoja.left = new Rama(15)
Arbol.root.right.hoja.left.hoja = new Hoja(2)
Arbol.root.left.hoja.left = new Rama(13)
Arbol.root.left.hoja.right = new Rama(14)
Arbol.root.left.hoja.left.hoja = new Hoja(1)
Arbol.root.left.hoja.right.hoja = new Hoja(3)


println('\nRecorrido preorder')
println(Arbol.preorder(Arbol.root))
println('\nRecorrido postorder')
println(Arbol.postorder(Arbol.root))
Arbol.esMaxHeapSimetrico(Arbol.root)
