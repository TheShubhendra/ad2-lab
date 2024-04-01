import java.util.ArrayList;
import java.util.List;
public class StringMatching {
	
	public static String removeDuplicates(String s) {
//		StringBuilder sb  = new StringBuilder();
		int k=0;
		boolean[] seen = new boolean[52];
		for(int i=0 ; i<s.length(); i++) {
			char ch = s.charAt(i);
			int idx = Character.isLowerCase(ch)? (int) ch -'a': (int) ch - 'A' + 26;
			if(!seen[idx]) {
//				sb.append(ch);
				seen[idx] = true;
			}else {
				s = s.substring(0, i) + s.substring(i+1, s.length());
				
			}
		}
		return s;
	}
	
	
	
	public static boolean hasUniqueCharaters(String s) {
		boolean[] freq = new boolean[26];
		for(char ch: s.toCharArray()) {
			ch = Character.toLowerCase(ch);
			if(freq[(int) ch - 'a']) {
				return false;
			}
			freq[(int) ch- 'a'] = true;
		}
		return true;
	}
	
	public static int encode(String s) {
		int res = 0;
		for(char ch: s.toCharArray()) {
			res*=10;
			res+=(int)ch - 'a' + 1;
		}
		return res;
	}
	
	public static List<Integer> robin_karp(String s, String pat){
		List<Integer> res = new ArrayList<Integer>();
		int m = pat.length();
		int pnum = encode(pat);
		int tnum = encode(s.substring(0, m));
		
		if(pnum==tnum) {
			res.add(0);
		}
		for(int i=1; i<s.length() - pat.length(); i++) {
			System.out.println(s.charAt(i-1));
//				tnum = (tnum - (int) Math.pow(10, m-1) * s.charAt(i-1) - 'a' + 1)*10 + s.charAt(i+m-1) - 'a' + 1;
				tnum = (tnum % (int) Math.pow(10, m-1) )*10 + s.charAt(i+m-1) - 'a' + 1;
				if(pnum==tnum) {
					res.add(i);
				}
				
		}
		return res;
	}

	public static List<Integer> find(String s, String pat) {
		List<Integer> res = new ArrayList<Integer>();
		for(int i=0; i<s.length() - pat.length(); i++) {
				boolean matched=true;
				for(int j=0; j<pat.length(); j++) {
					if(s.charAt(i+j)!=pat.charAt(j)) {
						matched=false;
						break;
					}
				}
				if(matched) {
					res.add(i);
				}
				
		}
		return res;
	}
	
	public static void main(String[] args) {
//		String s = "abcaabccabcba";
//		String pat = "abc";
		
		String s = "abacbcaccbcabc";
		// 12132313323123
		String p = "cbc";
//		System.out.println("pat: "+ encode(p));
//		System.out.println("string: "+ encode(s.substring(0, 3)));
		List<Integer> res  = robin_karp(s,p);
		for(int shift: res) {
			System.out.println("Pattern found at shift: "+ shift);
		}
		
		System.out.println("Apple has unique characters ?: " + hasUniqueCharaters("ApPle"));
		System.out.println("Mango has unique characters ?: " + hasUniqueCharaters("Mango"));
		System.out.println("Aplicability: " + removeDuplicates("Aplicability") );
	}

}
