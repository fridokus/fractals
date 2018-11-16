
public class Formler
{
	public static int isEscape(Complex c, Complex f, int iterations)
	{
		for(int i=0; i<=iterations; ++i)
		{
			f=f.times(f).plus(c);
			if(Math.max(c.abs(),2)<f.abs())
				return i;
		}
		return -1;
	}
}
