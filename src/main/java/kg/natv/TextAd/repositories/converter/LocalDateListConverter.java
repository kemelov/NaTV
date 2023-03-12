package kg.natv.TextAd.repositories.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class LocalDateListConverter implements AttributeConverter<List<LocalDate>, String> {
    private static final String DATE_DELIMITER = ",";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public String convertToDatabaseColumn(List<LocalDate> dates) {
        if (dates == null || dates.isEmpty()) {
            return null;
        }
        return dates.stream()
                .map(FORMATTER::format)
                .collect(Collectors.joining(DATE_DELIMITER));
    }

    @Override
    public List<LocalDate> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return Collections.emptyList();
        }
        String[] dateTokens = dbData.split(DATE_DELIMITER);
        List<LocalDate> dates = new ArrayList<>(dateTokens.length);
        for (String dateToken : dateTokens) {
            dates.add(LocalDate.parse(dateToken, FORMATTER));
        }
        return dates;
    }
}
