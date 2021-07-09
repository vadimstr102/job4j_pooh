package ru.job4j.pooh;

/**
 * Интерфейс сервисов для обработки запросов.
 *
 * @author Vadim Timofeev
 */
public interface Service {
    Resp process(Req req);
}
