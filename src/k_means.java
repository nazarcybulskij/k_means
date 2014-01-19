import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import javax.lang.model.element.Element;

public class k_means {
	private Random R = new Random();
	private int numberOfClasses;
	private int numberOfClassesOf;
	private ArrayList<ArrayList<element>> data;

	public ArrayList<ArrayList<element>> generateData(int numberOfClasses,
			int numberOfClassesOf) {
		this.numberOfClasses = numberOfClasses;
		this.numberOfClassesOf = numberOfClassesOf;
		data = new ArrayList<ArrayList<element>>();
		for (int j = 0; j < numberOfClassesOf; j++) {
			data.add(new ArrayList<element>());
			float centerX = (float) (R.nextInt(600));
			float centerY = (float) (R.nextInt(600));
			for (int i = 0; i < numberOfClasses; i++) {

				data.get(j).add(
						new element((int) (R.nextGaussian() * 50 + centerX),
								(int) (R.nextGaussian() * 50 + centerY), j));

			}
		}
		return data;

	}

	private Double dist(element a, element b) {
		return Math.sqrt((a.X - b.X) * (a.X - b.X) + (a.Y - b.Y) * (a.Y - b.Y));
	}

	public int getClassIndex(int X, int Y) {
		ArrayList<ArrayList<Double>> dist = new ArrayList<ArrayList<Double>>();
		element tempElement = new element(X, Y, 0);
		for (ArrayList<element> count_Element : data) {
			dist.add(new ArrayList<Double>());
			for (element i_element : count_Element) {
				dist.get(i_element.class_id).add(dist(tempElement, i_element));
			}
		}

		for (int i = 0; i < numberOfClassesOf; i++)
			Collections.sort(dist.get(i));

		LinkedList<Double> Sum = new LinkedList<Double>();
		Double sum_temp = 0.0d;
		for (int i = 0; i < numberOfClassesOf; i++) {
			sum_temp = 0.0d;
			for (int j = 0; j < 5; j++) {
				sum_temp += dist.get(i).get(j);
			}
			Sum.add(sum_temp);
		}
		Double min_element = Collections.min(Sum);

		for (int i = 0; i < numberOfClassesOf; i++) {
			if (min_element == Sum.get(i)) {
				return i;
			}
		}
		return -1;

	}
}