package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Класс-сервис. Обрабатывает запрос, отдаёт ответ.
 * Применяется одна очередь для всех получателей.
 * Уникальное сообщение может быть прочитано, только одним получателем.
 *
 * @author Vadim Timofeev
 */
public class QueueService implements Service {
    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        String method = req.method();
        String name = req.name();
        String text = req.text();
        String response = "Incorrect request";
        queue.putIfAbsent(name, new ConcurrentLinkedQueue<>());
        if (method.equalsIgnoreCase("POST")) {
            queue.get(name).offer(text);
            response = text;
        } else if (method.equalsIgnoreCase("GET")) {
            response = queue.get(name).poll();
            if (response == null) {
                response = "No messages";
            }
        }
        return new Resp(response, 200);
    }
}
