# Fractals

Generating the mandelbrot and julia fractals. Started in a personal project done 2011.

## How to use

Compile using javac. Then run

``java FraktalMain``

Use ``set mode`` to choose between Mandelbrot and Julia. Use ``repaint`` to re-generate the graph.

Set the number of iterations to a lower value to make the graph generate faster, but with lower resolution.

## Some math of the Mandelbrot set

The function `f` is defined as

``f(z) = z ** 2 + c``,

where `c` is a complex constant and `z` is a complex variable.

The color for each pixel `z` on the complex plane will be decided by the number `x`, where `x` is the first integer > 0 for which

``f ** x (z) > max(|c|, 2)``,

where ``f ** x (z)`` means that f has been applied `x` times on the complex number `z`.


Please read about ``Mandelbrot set`` and ``Julia set`` on wikipedia for more info.
