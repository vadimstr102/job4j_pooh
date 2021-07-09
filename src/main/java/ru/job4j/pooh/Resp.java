package ru.job4j.pooh;

/**
 * Класс служит для составления ответа от сервиса.
 *
 * @author Vadim Timofeev
 */
public class Resp {
    private final String text;
    private final int status;

    public Resp(String text, int status) {
        this.text = text;
        this.status = status;
    }

    public String text() {
        return text;
    }

    public int status() {
        return status;
    }
}
