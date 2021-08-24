package scala.crusader728.leetcode

object ComplexNumberMultiplication {
  private case class Complex(real: Int, imaginary: Int)

  private trait Semigroup[M] {
    def mappend(m1: M, m2: M): M
  }

  private trait Reader[A] {
    def read(str: String): A
  }

  private trait Writer[A] {
    def write(a: A): String
  }

  private implicit object ComplexSemigroup extends Semigroup[Complex] {
    override def mappend(m1: Complex, m2: Complex): Complex = {
      val r1 = m1.real * m2.real
      val r2 = m1.imaginary * m2.imaginary
      val i1 = m1.real * m2.imaginary
      val i2 = m2.real * m1.imaginary
      Complex(r1 - r2, i1 + i2)
    }
  }

  private implicit object ComplexWriter extends Writer[Complex] {
    override def write(a: Complex): String = s"${a.real}+${a.imaginary}i"
  }

  private implicit object ComplexReader extends Reader[Complex] {
    override def read(str: String): Complex = {
      val posOfPlus = str.indexOf('+')
      val posOfi = str.indexOf('i')
      Complex(str.substring(0, posOfPlus).toInt, str.substring(posOfPlus + 1, posOfi).toInt)
    }
  }

  private trait Ops[A] {
    def self: A
  }

  private trait SemigroupOps[A] extends Ops[A] {
    def *(that: A): A
  }

  private implicit def toSemigroupOps(c: Complex)(implicit S: Semigroup[Complex]): SemigroupOps[Complex] = new SemigroupOps[Complex] {
    override def *(that: Complex): Complex = S.mappend(self, that)

    override def self: Complex = c
  }

  private trait WriterOps[A] extends Ops[A] {
    def writeToString: String
  }

  private implicit def toWriteOps(c: Complex)(implicit W: Writer[Complex]): WriterOps[Complex] = new WriterOps[Complex] {
    override def self: Complex = c

    override def writeToString: String = W.write(c)
  }

  private implicit class ComplexStringReader(val s: String) extends AnyVal {
    def toComplex: Complex = {
      val complexReader = implicitly[Reader[Complex]]
      complexReader.read(s)
    }
  }

  def complexNumberMultiply(num1: String, num2: String): String = {
    (num1.toComplex * num2.toComplex).writeToString
  }
}
