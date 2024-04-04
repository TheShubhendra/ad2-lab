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
		int n =s.length();
		int pnum = 0;
		int tnum = 0;
		int h=1;
		int p= 13;
		for(int i=0; i<m; i++) {
			pnum = (pnum*10 + pat.charAt(i))%p;
			tnum = (tnum*10 + s.charAt(i))%p;
			if(i<m-1) {
				h = (h-1)%p;
			}
		}
		
		for(int i=0; i<=n - m; i++) {
				if(pnum==tnum) {
					int k=i;
					while(k<i+m && pat.charAt(k-i)==s.charAt(k)) {
						k++;
					}
					if(k==i+m) {
						res.add(i);
					}
				}
				if (i<n-m) {
					tnum = ((tnum -  s.charAt(i) *h)*10 + s.charAt(i+m))%p;
					if(tnum<0)
						tnum+=p;					
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
		String s = "abcaabccabcba";
		String p = "abc";
//		String s = "abacbcaccbcabc";
//		String p = "cbc";
//		System.out.println("pat: "+ encode(p));
//		System.out.println("string: "+ encode(s.substring(0, 3)));
		List<Integer> res  = robin_karp(s,p);
		for(int shift: res) {
			System.out.println("Pattern found at shift: "+ shift);
		}
		
//		System.out.println("Apple has unique characters ?: " + hasUniqueCharaters("ApPle"));
//		System.out.println("Mango has unique characters ?: " + hasUniqueCharaters("Mango"));
//		System.out.println("Aplicability: " + removeDuplicates("Aplicability") );
	}

}
