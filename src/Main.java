import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Main {

	public static final int POPULATION_SIZE = 8;
	public static final String POLY_BENCHMARK = "Poly";
	public static final String NASA_BENCHMARK = "Nasa";
	public static final String TUNING = "Tuning";
	public static final String TUNING_FLAG = "-Tuning_Analysis";
	public static final String INLINING_EXPANTION = "tinline=mode=1:depth:0:pragma=0:foronly=1:complement=0:functions=";

	private static Population population;
	// private static boolean bestoptions;
	// test
	private static ArrayList<String> combinations;

	private static boolean bestoptions;
	private static String nameApplication;
	private static boolean isPoly = false;
	private static String LoopId;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// System.out.print("Please choose what you want to do: \n (1) Genetic Algorithm
		// \n (2) Exhaustive Search \n (3) Combine Elimnation");
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// String[] paramters = br.readLine().split(" ");
		// System.out.print("Please write the application you want to apply the
		// algorithm to: ");

		String algorithm = args[0];
		String benchMark = args[1];
		nameApplication = args[2];
		String className = args[3];
		String tuningFlag = args[4];
		String loopBegining = args[5];
		String windowsSize = args[6];

		if (benchMark.toLowerCase().equals(POLY_BENCHMARK.toLowerCase())) {
			isPoly = true;
		}
		System.out.println(
				"Entry4: " + algorithm + " " + benchMark + " " + nameApplication + " " + className + " " + tuningFlag);

		if (algorithm.equals("3")) {

			combinedElimination(nameApplication, className, algorithm, benchMark, tuningFlag, loopBegining,
					windowsSize);
		}

	}

	public static void bubbleSort(ArrayList<Chromosome> chrom) {
		int n = chrom.size();
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (chrom.get(j).getFitValues() < chrom.get(j + 1).getFitValues()) {
					// swap arr[j+1] and arr[j]
					Chromosome temp = chrom.get(j);
					chrom.set(j, chrom.get(j + 1));
					chrom.set(j + 1, temp);
				}
			}
		}

	}

	static void saveValuesArray(int arr[], int n) {
		String combination = "";
		for (int i = 0; i < n; i++) {
			combination += arr[i] + " ";

		}
		combinations.add(combination);
	}

	public static void exportInfo(String nameApplication, String results, String exportPath, String timeTaken,
			String className, String algorithm) {

		ProcessBuilder processBuilder = new ProcessBuilder(exportPath, nameApplication.toUpperCase(),
				nameApplication.toLowerCase(), results, timeTaken, className, algorithm);

		// processBuilder.directory(new File(path));
		try {
			Process process = processBuilder.start();
			process.waitFor();
			process.destroy();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Function to generate all binary strings
	public static void generateAllBinaryStrings(int n, int arr[], int i) {
		if (i == n) {
			saveValuesArray(arr, n);
			return;
		}
		// First assign "0" at ith position
		// and try for all other permutations
		// for remaining positions
		arr[i] = 0;
		generateAllBinaryStrings(n, arr, i + 1);

		// And then assign "1" at ith position
		// and try for all other permutations
		// for remaining positions
		arr[i] = 1;
		generateAllBinaryStrings(n, arr, i + 1);

	}

	public static double valueSpeedUp(String nameApplication, String className, String algorithm, String loopBegining,
			String windowsSize) throws IOException {
		String currentDir = System.getProperty("user.dir");
		File file = null;
		if (isPoly) {
			file = new File(currentDir + "/PolyBenchC-4.2.1/" + nameApplication + "/logFiles/log_"
					+ nameApplication.toLowerCase() + "_" + className + "_" + algorithm + "_" + loopBegining + "_w_"
					+ windowsSize + ".txt");
		} else {

			file = new File(currentDir + "/SNU_NPB-1.0.3/NPB3.3-SER-C/" + nameApplication + "/logFiles/log_"
					+ nameApplication.toLowerCase() + "_" + className + "_" + algorithm + "_" + loopBegining + "_w_"
					+ windowsSize + ".txt");
		}

		BufferedReader br = new BufferedReader(new FileReader(file));
		String st = "";
		String result = "";
		while ((st = br.readLine()) != null) {
			// System.out.println(st);
			if (st.contains("TimeInstrument")) {
				String[] speedup = st.replace(" ", "").split("=");
				// System.out.println("-----"+speedup.toString());
				String[] loopName = speedup[0].split("-");
				LoopId = loopName[1];
				// for (int index = 0; index < speedup.length && speedup[index] != ""; index++)
				// {
				// System.out.println(speedup[index]);
				// System.out.println("-----");
				// }
				result = speedup[1];
				// System.out.println(speedup.toString());
				// System.out.println("-----");
			}
		}
		return Double.parseDouble(result);

	}

	public static double callCetus(String techniques, String Path, String nameApplication, String className,
			String algorithm, String loopBegining, String windowsSize) {

		String loopId = loopBegining.replace("#", "_");
		double defaultFitnessValue = 0.0;
		ProcessBuilder processBuilder = new ProcessBuilder(Path, nameApplication.toUpperCase(),
				nameApplication.toLowerCase(), techniques, className, algorithm, loopId, windowsSize);

		// processBuilder.directory(new File(path));
		try {
			Process process = processBuilder.start();
			process.waitFor();
			process.destroy();
			defaultFitnessValue = valueSpeedUp(nameApplication, className, algorithm, loopId, windowsSize);

			System.out.println("The Execution Time is: " + defaultFitnessValue);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return defaultFitnessValue;
	}

	public static void bubbleSortArray(ArrayList<Chromosome> list) {
		int n = list.size();

		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (list.get(j).getFitValues() > list.get(j + 1).getFitValues()) {
					// Swap list[j] and list[j + 1]
					Chromosome temp = list.get(j);
					list.set(j, list.get(j + 1));
					list.set(j + 1, temp);
				}
			}
		}
	}

	public static void combinedElimination(String nameApplication, String className, String algorithm, String benchMark,
			String tuningFlag, String loopBegining, String windowsSize) {

		long startTime = System.currentTimeMillis();
		String currentDir = System.getProperty("user.dir");
		String path = "";
		String pathAnswer = "";
		if (benchMark.toLowerCase().equals("poly")) {
			// System.out.println("Is equal");
			path = currentDir + "/bashFiles/ScriptPoly.sh";
			pathAnswer = currentDir + "/bashFiles/cetusAnswerPoly.sh";
		} else {
			// System.out.println("Not is equal");
			path = currentDir + "/bashFiles/ScriptCombineElimination.sh";
			pathAnswer = currentDir + "/bashFiles/cetusAnswer.sh";
		}

		String techniques = "";

		if (tuningFlag.equals(TUNING)) {
			techniques += "-alias=2 " + TUNING_FLAG + "=" + loopBegining + ";" + windowsSize;
		}

		String aliasNumber = "0";
		String loopIdInlining = loopBegining.replace("#0", "");
		for (int i = 0; i < Integer.parseInt(windowsSize); i++) {
			if (nameApplication.equals("UA")) {
				techniques += ";alias#0=3,"
						+ "induction#0=3,privatize#0=2,parallelize-loops#0=1,reduction#0=2,range#0=1";
				aliasNumber = "3";
			} else {
				aliasNumber = "2";
				if (i > 0) {
					techniques += "&alias#" + i + "=2," +"tinline=mode=1:depth:0:pragma=0:foronly=1:complement=0:functions="+loopIdInlining+",induction#" + i + "=3,privatize#" + i
							+ "=2,parallelize-loops#" + i + "=1,reduction#" + i + "=2,loop_interchange#" + i
							+ "=1,range#" + i + "=1";

				} else {
					techniques += ";alias#" + i + "=2," +"tinline=mode=1:depth:0:pragma=0:foronly=1:complement=0:functions="+loopIdInlining+ ",induction#" + i + "=3,privatize#" + i
							+ "=2,parallelize-loops#" + i + "=1,reduction#" + i + "=2,loop_interchange#" + i
							+ "=1,range#" + i + "=1";
				}

			}
		}

		double defualtET = callCetus(techniques, path, nameApplication, className, algorithm, loopBegining,
				windowsSize);

		//
		ArrayList<String> eachTechnique = new ArrayList<String>(
				Arrays.asList("induction", "tinline", "privatize", "parallelize-loops", "reduction", "loop_interchange",
						"range"));

		Hashtable<Double, String> RENegative = new Hashtable<Double, String>();

		String tuningFlagTechnique = "-alias=2 ";
		if (tuningFlag.equals(TUNING)) {
			tuningFlagTechnique += TUNING_FLAG;
		}

		ArrayList<String> listOptimizationsWS = windowsSize(Integer.parseInt(windowsSize));
		ArrayList<Technique> listTechniqueWithValues = windowsSizeTechniquewithValues(aliasNumber,
				Integer.parseInt(windowsSize), loopIdInlining);

		ArrayList<Technique> ASNWER = second(listOptimizationsWS, listTechniqueWithValues, eachTechnique, RENegative,
				path, defualtET,
				nameApplication, className, algorithm, tuningFlagTechnique, loopBegining, windowsSize);

		// System.out.println(pathAnswer);
		long finalTime = System.currentTimeMillis();
		String timeTaken = (finalTime - startTime) + "";

		writeAnswerFile(pathAnswer, nameApplication, ASNWER.toString() + " Windows Size: " + windowsSize, timeTaken,
				className, algorithm, windowsSize, loopBegining);

	}

	public static ArrayList<String> windowsSize(int Wsize) {

		ArrayList<String> listOptimizations = new ArrayList<String>();
		for (int i = 0; i <= Wsize; i++) {
			String optAlias = "alias#" + i;
			String inline = "tinline";
			String optInduction = "induction#" + i;
			String optPrivatization = "privatize#" + i;
			String optParallelize = "parallelize-loops#" + i;
			String optReduction = "reduction#" + i;
			String optLoopInter = "loop_interchange#" + i;
			String optRange = "range#" + i;
			listOptimizations.add(optAlias);
			listOptimizations.add(inline);
			listOptimizations.add(optInduction);
			listOptimizations.add(optPrivatization);
			listOptimizations.add(optParallelize);
			listOptimizations.add(optReduction);
			listOptimizations.add(optLoopInter);
			listOptimizations.add(optRange);
		}

		return listOptimizations;

	}

	public static ArrayList<Technique> windowsSizeTechniquewithValues(String aliasNumber, int Wsize,
			String loopNameInline) {

		ArrayList<Technique> listOptimizations = new ArrayList<Technique>();
		for (int i = 0; i < Wsize; i++) {

			Technique alias = new Technique("alias#" + i, aliasNumber);
			Technique inline = new Technique("tinline", "1", loopNameInline);
			Technique induction = new Technique("induction#" + i, "3");
			Technique privatization = new Technique("privatize#" + i, "2");
			Technique parallelize = new Technique("parallelize-loops#" + i, "1");
			Technique reduction = new Technique("reduction#" + i, "2");
			Technique loopInter = new Technique("loop_interchange#" + i, "1");
			Technique range = new Technique("range#" + i, "1");

			listOptimizations.add(alias);
			listOptimizations.add(inline);
			listOptimizations.add(induction);
			listOptimizations.add(privatization);
			listOptimizations.add(parallelize);
			listOptimizations.add(reduction);
			listOptimizations.add(loopInter);
			listOptimizations.add(range);
		}

		return listOptimizations;

	}

	public static void writeAnswerFile(String Path, String nameApplication, String answer, String timeTaken,
			String ClassName, String algorithm, String windowsSize, String loopBegining) {

		String loopId = LoopId.replace("#", "_");
		String loopIdBegining = loopBegining.replace("#", "_");
		ProcessBuilder processBuilder = new ProcessBuilder(Path, nameApplication.toUpperCase(),
				nameApplication.toLowerCase(), answer, timeTaken, ClassName, algorithm, loopId, windowsSize,
				loopIdBegining);
		try {
			Process process = processBuilder.start();
			process.waitFor();
			process.destroy();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static ArrayList<Technique> second(ArrayList<String> listOptimizations,
			ArrayList<Technique> listTechniqueWithValues, ArrayList<String> eachTechnique,
			Hashtable<Double, String> RENegative, String path, double defualtET, String nameApplication,
			String className, String algorithm, String tuningFlag, String loopBegining, String windowsSize) {

		// System.out.println(listTechniqueWithValues.toString());

		RENegative = new Hashtable<Double, String>();

		for (int i = 0; i < listTechniqueWithValues.size(); i++) {
			String optimizationName = listTechniqueWithValues.get(i).getName();
			String value = listTechniqueWithValues.get(i).getValue();
			if (!optimizationName.startsWith("alias#") && !value.equals("0") ) {

				listTechniqueWithValues.get(i).setValue("0");
				String TechniquesComplete = techniquesCompleted(listTechniqueWithValues);
				String TechniqueCompletedNoSpace = TechniquesComplete.replace(" ", "");

				String techniquesAll = tuningFlag + "=" + loopBegining + ";" + windowsSize + ";"
						+ TechniqueCompletedNoSpace + "";
				double techiqueET = callCetus(techniquesAll, path, nameApplication, className, algorithm, loopBegining,
						windowsSize);
				double RE = relativeImprovementPercentage(techiqueET, defualtET);
				listTechniqueWithValues.get(i).setValue(value);
				if (RE < 0) {
					RENegative.put(RE, optimizationName);
				}

			}

		}
		if (RENegative.size() != 0) {
			List<Transformation> ETSorted = SortingAlgorithm(RENegative);
			// System.out.println(ETSorted.toString());
			Third(listOptimizations, listTechniqueWithValues, eachTechnique, ETSorted, RENegative, path,
					nameApplication, listTechniqueWithValues, className,
					algorithm, tuningFlag, loopBegining, windowsSize);
		} 

		return listTechniqueWithValues;

	}

	public static String techniquesCompleted(ArrayList<Technique> listTechniqueWithValues) {
		String answer = "";

		for (int i = 0; i < listTechniqueWithValues.size(); i++) {
			Technique technique = listTechniqueWithValues.get(i);

			if (technique.getName().startsWith("range#") && i + 1 == listTechniqueWithValues.size()) {
				answer += technique.getName() + "=" + technique.getValue();
			} else {
				answer += technique.toString() + ",";
			}

			if (technique.getName().startsWith("range#") && i + 1 < listTechniqueWithValues.size()) {
				answer += "&";
			}

		}

		return answer;

	}

	public static void Third(ArrayList<String> listOptimizations, ArrayList<Technique> listTechniqueWithValues,
			ArrayList<String> eachTechnique, List<Transformation> ETSorted,
			Hashtable<Double, String> RENegative, String path, String nameApplication,
			ArrayList<Technique> techniqueWithValues, String className, String algorithm, String tuningFlag,
			String loopBegining, String windowsSize) {

		delete(ETSorted.get(0).getName(), listOptimizations);
		editTechnique(ETSorted.get(0).getName(), listTechniqueWithValues);
		double defaultET = 0.0;

		String techniquesAll = flagsEnableCetus(listTechniqueWithValues, tuningFlag, loopBegining, windowsSize);
		defaultET = callCetus(techniquesAll, path, nameApplication, className, algorithm, loopBegining, windowsSize);

		for (int i = 1; i < ETSorted.size(); i++) {
			double RE = relativeImprovementPercentage(ETSorted.get(i).getValue(), defaultET);
			if (RE < 0) {
				delete(ETSorted.get(i).getName(), listOptimizations);
				editTechnique(ETSorted.get(i).getName(), listTechniqueWithValues);
			}
		}
		techniquesAll = flagsEnableCetus(listTechniqueWithValues, tuningFlag, loopBegining, windowsSize);
		defaultET = callCetus(techniquesAll, path, nameApplication, className, algorithm, loopBegining, windowsSize);

		second(listOptimizations, listTechniqueWithValues, eachTechnique, RENegative, path, defaultET, nameApplication,
				className, algorithm, tuningFlag, loopBegining, windowsSize);

	}

	public static String flagsEnableCetus(ArrayList<Technique> listTechniqueWithValues, String tuningFlag, String loopBegining,
			String windowsSize) {
		String TechniquesComplete = techniquesCompleted(listTechniqueWithValues);
		String TechniqueCompletedNoSpace = TechniquesComplete.replace(" ", "");
		String techniquesAll = tuningFlag + "=" + loopBegining + ";" + windowsSize + ";" + TechniqueCompletedNoSpace
				+ "";
		return techniquesAll;
	}

	public static void delete(String ETtechniqueName, ArrayList<String> eachTechnique) {
		for (int i = 0; i < eachTechnique.size(); i++) {
			if (eachTechnique.get(i).equals(ETtechniqueName)) {
				eachTechnique.remove(i);
			}
		}
	}

	public static double relativeImprovementPercentage(double techiqueET, double defaultET) {

		double result = ((techiqueET - defaultET) / defaultET) * 100;
		return result;

	}

	public static double relativeImprovementPercentageGenetic(double defaultET, double techiqueET) {

		double result = ((defaultET - techiqueET) / defaultET) * 100;
		return result;

	}

	public static void editTechnique(String name, ArrayList<Technique> techniqueWithValues) {

		for (int i = 0; i < techniqueWithValues.size(); i++) {
			if (techniqueWithValues.get(i).getName().equals(name)) {
				techniqueWithValues.get(i).setValue("0");

			}
		}

	}

	// Sorting descending
	public static List<Transformation> SortingAlgorithm(Hashtable<Double, String> RENegative) {
		List<Double> list = new ArrayList<Double>();
		list.addAll(RENegative.keySet());

		Double[] RIPS = RENegative.keySet().toArray(new Double[0]);
		Arrays.sort(RIPS);

		List<Transformation> transformations = new ArrayList<Transformation>();
		for (int i = 0; i < RIPS.length; i++) {
			Transformation trans = new Transformation(RENegative.get(RIPS[i]), RIPS[i]);
			transformations.add(trans);
		}

		return transformations;
	}

}