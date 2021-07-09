package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Класс-сервис. Обрабатывает запрос, отдаёт ответ.
 * Для каждого получателя создаётся уникальная очередь потребления.
 *
 * @author Vadim Timofeev
 */
public class TopicService implements Service {
    private final ConcurrentHashMap<Integer, ConcurrentHashMap<String, ConcurrentLinkedQueue<String>>> queue =
            new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        String method = req.method();
        String name = req.name();
        String text = req.text();
        String response = "Incorrect request";
        int id = req.id();
        if (method.equalsIgnoreCase("POST")) {
            queue.values().forEach(themes -> {
                themes.putIfAbsent(name, new ConcurrentLinkedQueue<>());
                themes.get(name).offer(text);
            });
            response = text;
        }
        if (method.equalsIgnoreCase("GET")) {
            queue.putIfAbsent(id, new ConcurrentHashMap<>());
            queue.get(id).putIfAbsent(name, new ConcurrentLinkedQueue<>());
            response = queue.get(id).get(name).poll();
            if (response == null) {
                response = "No messages";
            }
        }
        return new Resp(response, 200);
    }
}
