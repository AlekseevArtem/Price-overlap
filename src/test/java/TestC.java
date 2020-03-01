
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;

public class TestC {
    private final DateTimeFormatter fmt = new DateTimeFormatterBuilder()
            .appendPattern("dd.MM.yyyy HH:mm:ss")
            .toFormatter();

    @Test
    public void TestFromExample() {
        //current
        Cost first = new Cost (0, "122856", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("31.01.2013 23:59:59", fmt), 11000);
        Cost second = new Cost (0, "122856", 2, 1,
                LocalDateTime.parse("10.01.2013 00:00:00", fmt), LocalDateTime.parse("20.01.2013 23:59:59", fmt), 99000);
        Cost third = new Cost (0, "6654", 1, 2,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("31.01.2013 00:00:00", fmt), 5000);
        List<Cost> current = new ArrayList<Cost> () ;
        current.add(first);
        current.add(second);
        current.add(third);
        //input
        Cost firstNew = new Cost (0, "122856", 1, 1,
                LocalDateTime.parse("20.01.2013 00:00:00", fmt), LocalDateTime.parse("20.02.2013 23:59:59", fmt), 11000);
        Cost secondNew = new Cost (0, "122856", 2, 1,
                LocalDateTime.parse("15.01.2013 00:00:00", fmt), LocalDateTime.parse("25.01.2013 23:59:59", fmt), 92000);
        Cost thirdNew = new Cost (0, "6654", 1, 2,
                LocalDateTime.parse("12.01.2013 00:00:00", fmt), LocalDateTime.parse("13.01.2013 00:00:00", fmt), 4000);
        List<Cost> input = new ArrayList<Cost> () ;
        input.add(firstNew);
        input.add(secondNew);
        input.add(thirdNew);
        //result
        Cost firstResult = new Cost (0, "122856", 2, 1,
                LocalDateTime.parse("10.01.2013 00:00:00", fmt), LocalDateTime.parse("15.01.2013 00:00:00", fmt), 99000);
        Cost secondResult = new Cost (0, "6654", 1, 2,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("12.01.2013 00:00:00", fmt), 5000);
        Cost thirdResult = new Cost (0, "6654", 1, 2,
                LocalDateTime.parse("12.01.2013 00:00:00", fmt), LocalDateTime.parse("13.01.2013 00:00:00", fmt), 4000);
        Cost fourthResult = new Cost (0, "6654", 1, 2,
                LocalDateTime.parse("13.01.2013 00:00:00", fmt), LocalDateTime.parse("31.01.2013 00:00:00", fmt), 5000);
        Cost fifthResult = new Cost (0, "122856", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("20.02.2013 23:59:59", fmt), 11000);
        Cost sixthResult = new Cost (0, "122856", 2, 1,
                LocalDateTime.parse("15.01.2013 00:00:00", fmt), LocalDateTime.parse("25.01.2013 23:59:59", fmt), 92000);
        List<Cost> expect = new ArrayList<Cost>();
        expect.add(firstResult);
        expect.add(secondResult);
        expect.add(thirdResult);
        expect.add(fourthResult);
        expect.add(fifthResult);
        expect.add(sixthResult);
        List<Cost> result = BigCode.addNewCosts(current , input);
        Assert.assertThat(result, is(expect));
    }

    @Test
    public void TestFromExamples3() {
        //current
        Cost first = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("06.01.2013 00:00:00", fmt), 80);
        Cost second = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("06.01.2013 00:00:00", fmt), LocalDateTime.parse("16.01.2013 00:00:00", fmt), 87);
        Cost third = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("16.01.2013 00:00:00", fmt), LocalDateTime.parse("31.01.2013 00:00:00", fmt), 90);
        List<Cost> current = new ArrayList<Cost>();
        current.add(first);
        current.add(second);
        current.add(third);
        //input
        Cost firstNew = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("11.01.2013 00:00:00", fmt), 80);
        Cost secondNew = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("11.01.2013 00:00:00", fmt), LocalDateTime.parse("21.01.2013 00:00:00", fmt), 85);
        List<Cost> currentNew = new ArrayList<Cost>();
        currentNew.add(firstNew);
        currentNew.add(secondNew);
        List<Cost> result = BigCode.addNewCosts(current, currentNew);
        System.out.println(result);
        //result
        Cost firstResult = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("21.01.2013 00:00:00", fmt), LocalDateTime.parse("31.01.2013 00:00:00", fmt), 90);
        Cost secondResult = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("11.01.2013 00:00:00", fmt), 80);
        Cost thirdResult = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("11.01.2013 00:00:00", fmt), LocalDateTime.parse("21.01.2013 00:00:00", fmt), 85);
        List<Cost> expect = new ArrayList<Cost>();
        expect.add(firstResult);
        expect.add(secondResult);
        expect.add(thirdResult);
        Assert.assertThat(result, is(expect));

    }
}