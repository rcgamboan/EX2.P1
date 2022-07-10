// Clase que implementa la representacion de numeros de Church
// Por cada numero de church se tienen dos atributos, su valor, que se trata de otro numero de Church y su sucesor
// Para representar un numero natural n, se usa el sucesor de un numero de church con valor "nulo" n veces
// Y a partir de un numero de church, para obtener su representacion natural se llama a su valor
// Hasta que se alcance un numeor de Church con valor Nulo
class Church {

    Church valor

    Church(Church valor) {
        this.valor = valor
    }

    Church sucesor(){
        return new Church(this)
    }

    int obtenerRepNatural(){
        int numero = 0
        Church actual = this.valor
        while (actual != null) {
            numero += 1
            actual = actual.valor
        }
        return numero
    }

    static Church crearChurch(int numero){
        Church church = new Church(null)
        int i = 0
        while (i < numero) {
            i += 1
            church = church.sucesor()
        }
        return church
    }

}

// Se itera hasta que i sea igual a la suma de la representacion natural de ambos numeros
Church sumaChurch(Church ch1, Church ch2){
    Church church = new Church(null)
    int i = 0
    while (i < (ch1.obtenerRepNatural() + ch2.obtenerRepNatural())) {
        i += 1
        church = church.sucesor()
    }
    return church
}
// Se itera hasta que i sea igual a la multiplicacion de la representacion natural de ambos numeros
Church multChurch(Church ch1, Church ch2){
    Church church = new Church(null)
    int i = 0
    while (i < (ch1.obtenerRepNatural() * ch2.obtenerRepNatural())) {
        i += 1
        church = church.sucesor()
    }
    return church
}
Church x = Church.crearChurch(9)
Church y = Church.crearChurch(10)
Church z = sumaChurch(x, y)
Church w = multChurch(x, y)
println(z.obtenerRepNatural())
println(w.obtenerRepNatural())
