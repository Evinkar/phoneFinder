package ru.front.frame.message;

public enum PhoneNumberMessages {
    NUMBER_RU("number", "Номер"),
    COUNTRY_INDEX_RU("countryIndex", "Индекс страны"),
    UPDATE_AT_RU("updatedAt", "Последнее обновление"),
    DATA_HUMANS_RU("dataHumans", "Последнее обновление"),
    FULL_NUMBER_RU("fullNumber", "Полный номер"),
    COUNTRY_TEXT_RU("countryText", "Название страны"),
    MAX_DATE_RU("maxDate", "Срок действия???"),
    STATUS_RU("status", "Статус");

    private final String id;
    private final String name;

    PhoneNumberMessages(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String get(String id) {
        for (PhoneNumberMessages messages : values()) {
            if (messages.id.equals(id)) {
                return messages.name;
            }
        }
        return null;
    }
}
