
public class ArraySum {

	public int sumOfArray(Integer[] myArray, int i) {
		if (i < 0)
			return 0;
		return myArray[i] + sumOfArray(myArray, i-1);
	}

}
