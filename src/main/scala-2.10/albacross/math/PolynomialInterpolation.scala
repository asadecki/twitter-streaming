package albacross.math

import breeze.linalg.{DenseMatrix, inv}
import breeze.numerics.pow

object PolynomialInterpolation {

  def calculateParameters(points: Map[Double, Double]): DenseMatrix[Double] = {
    val pointsNumber = points.size

    val input = points
      .keySet
      .map(value => (0 until pointsNumber).map(power => pow(value, power)))
      .map(_.toArray)

    val xMatrix = DenseMatrix.create(pointsNumber, pointsNumber, input.toArray.flatten)
    val yMatrix = DenseMatrix.create(pointsNumber, 1, points.values.toArray)

    val inversedXMatrix = inv(xMatrix).t
    inversedXMatrix * yMatrix
  }
}
