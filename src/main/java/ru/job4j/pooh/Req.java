package ru.job4j.pooh;

/**
 * Класс служит для парсинга запроса.
 *
 * @author Vadim Timofeev
 */
public class Req {
    private final String method;
    private final String mode;
    private final String name;
    private final String text;
    private final int id;

    private Req(String method, String mode, String name, String text, int id) {
        this.method = method;
        this.mode = mode;
        this.name = name;
        this.text = text;
        this.id = id;
    }

    public static Req of(String content) {
        String[] s1 = content.split(" ");
        String method = s1[0];
        String[] s2 = s1[1].split("/");
        String mode = s2[1];
        String name = s2[2];
        String[] s3 = content.split(System.lineSeparator());
        String text = s3[s3.length - 1];
        if (mode.equalsIgnoreCase("topic") && method.equalsIgnoreCase("GET")) {
            int id = Integer.parseInt(s2[3]);
            return new Req(method, mode, name, text, id);
        }
        return new Req(method, mode, name, text, -1);
    }

    public String method() {
        return method;
    }

    public String mode() {
        return mode;
    }

    public String name() {
        return name;
    }

    public String text() {
        return text;
    }

    public int id() {
        return id;
    }
}
