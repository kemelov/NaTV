package kg.natv.TextAd.repositories.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Converter
public class LocalDateSetConverter implements AttributeConverter<Set<LocalDate>, String> {
    private static final String DATE_DELIMITER = ",";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public String convertToDatabaseColumn(Set<LocalDate> dates) {
        if (dates == null || dates.isEmpty()) {
            return null;
        }
        return dates.stream()
                .map(FORMATTER::format)
                .collect(Collectors.joining(DATE_DELIMITER));
    }

    @Override
    public Set<LocalDate> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return Collections.emptySet();
        }
        String[] dateTokens = dbData.split(DATE_DELIMITER);
        Set<LocalDate> dates = new HashSet<>(dateTokens.length);
        for (String dateToken : dateTokens) {
            dates.add(LocalDate.parse(dateToken, FORMATTER));
        }
        return dates;
    }
}
