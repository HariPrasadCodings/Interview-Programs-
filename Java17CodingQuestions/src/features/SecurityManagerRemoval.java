package features;

public class SecurityManagerRemoval {
	public static void main(String[] args) {
		SecurityManager manager = System.getSecurityManager();
		System.out.println(manager);
	}

}
