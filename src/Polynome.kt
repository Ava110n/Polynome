import kotlin.math.*

class Polynome {
    var coeffs: DoubleArray
    var degree: Int

    constructor() {
        coeffs = doubleArrayOf(0.0)
        this.degree = 0
    }

    constructor(coeffs: DoubleArray) {
        this.coeffs = coeffs
        this.degree = coeffs.size - 1
    }

    override fun toString(): String {
        var str = ""
        for (i in 0..this.degree) {
            if (coeffs[i] == 0.0) continue
            str += { c: Double -> if (c > 0) "+" else "-" }(coeffs[i])
            if (str == "+") str = ""
            str += abs(coeffs[i])
            if (i < this.degree) str += "*x^${degree - i}"
        }
        return str
    }

    operator fun plus(other: Polynome): Polynome {
        var my_degree = max(this.degree, other.degree)
        var my_coeffs = DoubleArray(my_degree+1, { 0.0 })
        for (i in 0..my_degree) {
            var c = { c: DoubleArray, d: Int ->
                if (d < my_degree - i) 0.0
                else c[i - my_degree + d]
            }

            var x = c(this.coeffs, this.degree)
            var y = c(other.coeffs, other.degree)
            my_coeffs[i] = x + y
        }
        return Polynome(my_coeffs)
    }

    operator fun times(k: Double):Polynome{
        var my_coeffs = DoubleArray(this.degree+1, { 0.0 })
        for(i in 0..this.degree){
            my_coeffs[i] = this.coeffs[i]*k
        }
        return Polynome(my_coeffs)
    }
    operator fun Double.times(p: Polynome) = p.times(this)
    operator fun times(k: Int):Polynome = this.times(k*1.0)
}

fun main() {
    var a = Polynome(doubleArrayOf(1.0, 2.0, 3.0))
    var b = Polynome(doubleArrayOf(-1.0,6.0,7.0))
    println(a*3.0)
}
