package projectFile;

public class TestMain {
	
//	public static void main(string[] args) {
//		system.out.println("here");
//		int i;
//		string test1 = "ljava/util/list<te;>;";
//		string test2 = "ljava/util/list<*>;";
//		string test3 = "ljava/util/list<[ljava/util/list<ljava/lang/string;>;>;";
//		check(test1);
//		check(test2);
//		check(test3);
//	}
	
	
	public static void check(String s) {
		System.out.println("Running test on " + s);
		String[] res;
		res = s.split("[^\\w./$]");
		for (int i = 0; i < res.length; i++) {
			if (res[i].startsWith("T")) {
				continue;
			}
			if (res[i].length() == 0) {
				continue;
			}
			System.out.println(res[i].substring(1));
		}
		
	}
}
