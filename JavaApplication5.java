package laba4;

//import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.*;
import java.util.*;

public class JavaApplication5 {

	public int getIndexIfContains(ArrayList<HashSet<Integer>> clan, int firstPerson, int secondPerson) {
		for (int i = 0; i < clan.size(); ++i) {
			Set<Integer> enumeration = clan.get(i);
			if (enumeration.contains(firstPerson) || enumeration.contains(secondPerson))
				return i;
		}
		return -1;
	}

	public ArrayList<HashSet<Integer>> loadData(String file) {
		ArrayList<HashSet<Integer>> tribes = new ArrayList<HashSet<Integer>>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line;
			String[] branch;

			in.readLine();
			while ((line = in.readLine()) != null) {
				branch = line.split(" ");
				int firstPerson = Integer.parseInt(branch[0]);
				int secondPerson = Integer.parseInt(branch[1]);
				int index = getIndexIfContains(tribes, firstPerson, secondPerson);

				if (index == -1) {
					HashSet<Integer> newPeople = new HashSet<Integer>();
					newPeople.add(firstPerson);
					newPeople.add(secondPerson);
					tribes.add(newPeople);
				} else {
					Set<Integer> alreadySeenPeople = tribes.get(index);
					alreadySeenPeople.add(firstPerson);
					alreadySeenPeople.add(secondPerson);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tribes;
	}

	public List<String> getPersonsPairs(Set<Integer> byTribes, Set<Integer> byPeopleInTribes) {
		List<String> ls = new ArrayList<>();
		for (int firstPerson : byTribes) {
			for (int secondPerson : byPeopleInTribes) {
				if ((firstPerson % 2) != (secondPerson % 2)) {
					ls.add(firstPerson + "/" + secondPerson);
				}
			}
		}
		return ls;
	}

	public final int calculate(String file) {
		List<String> result = new ArrayList<>();
		ArrayList<HashSet<Integer>> tribes = loadData(file);

		for (int i = 0; i < tribes.size(); i++) {
			for (int j = i + 1; j < tribes.size(); ++j) {
				result.addAll(getPersonsPairs(tribes.get(i), tribes.get(j)));
			}
		}
		System.out.println(result);
		return result.size();
	}

	public JavaApplication5() {

		// * ********************************************************** */
		System.out.println("Count pairs: " + calculate("C:\\Users\\test\\eclipse-workspace\\algs4\\in.txt"));
	}

	public static void main(String[] args) {
		new JavaApplication5();
	}

}
