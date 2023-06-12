package designpatterns.builder;

public class Bureau_main {
    public static void main(String[] args) {
        try {
            Bureau bureau = new Bureau.BureauBuilder()
                    .setId_bureau(1)
                    .setSigle("ABC")
                    .setTel("+32123456789", "^(?:(?:\\+|00)32|0)\\s*[1-9](?:[\\s.-]*\\d{2}){4}$")
                    .build();

            System.out.println(bureau);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
