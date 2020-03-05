
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
        List<Cost> current = new ArrayList<> () ;
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
        List<Cost> input = new ArrayList<> () ;
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
        List<Cost> expect = new ArrayList<>();
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
    public void TestForOneCostMethod_FromExample1() {
        //current
        Cost first = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("28.01.2013 00:00:00", fmt), 80);
        //input
        Cost firstNew = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("05.01.2013 00:00:00", fmt), LocalDateTime.parse("16.01.2013 00:00:00", fmt), 90);
        List<Cost> currentNew = new ArrayList<>();
        currentNew.add(firstNew);
        //result
        Cost firstResult = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("05.01.2013 00:00:00", fmt), 80);
        Cost secondResult = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("05.01.2013 00:00:00", fmt), LocalDateTime.parse("16.01.2013 00:00:00", fmt), 90);
        Cost thirdResult = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("16.01.2013 00:00:00", fmt), LocalDateTime.parse("28.01.2013 00:00:00", fmt), 80);
        List<Cost> expect = new ArrayList<>();
        expect.add(firstResult);
        expect.add(secondResult);
        expect.add(thirdResult);
        List<Cost> result = BigCode.addNewCosts(first, currentNew);
        Assert.assertThat(result, is(expect));
    }

    @Test
    public void TestFromExamples2() {
        //current
        Cost first = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("16.01.2013 00:00:00", fmt), 100);
        Cost second = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("16.01.2013 00:00:00", fmt), LocalDateTime.parse("31.01.2013 00:00:00", fmt), 120);
        List<Cost> current = new ArrayList<>();
        current.add(first);
        current.add(second);
        //input
        Cost firstNew = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("07.01.2013 00:00:00", fmt), LocalDateTime.parse("24.01.2013 00:00:00", fmt), 110);
        List<Cost> currentNew = new ArrayList<>();
        currentNew.add(firstNew);
        //result
        Cost firstResult = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("07.01.2013 00:00:00", fmt), 100);
        Cost secondResult = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("24.01.2013 00:00:00", fmt), LocalDateTime.parse("31.01.2013 00:00:00", fmt), 120);
        Cost thirdResult = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("07.01.2013 00:00:00", fmt), LocalDateTime.parse("24.01.2013 00:00:00", fmt), 110);
        List<Cost> expect = new ArrayList<>();
        expect.add(firstResult);
        expect.add(secondResult);
        expect.add(thirdResult);
        List<Cost> result = BigCode.addNewCosts(current, currentNew);
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
        List<Cost> current = new ArrayList<>();
        current.add(first);
        current.add(second);
        current.add(third);
        //input
        Cost firstNew = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("11.01.2013 00:00:00", fmt), 80);
        Cost secondNew = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("11.01.2013 00:00:00", fmt), LocalDateTime.parse("21.01.2013 00:00:00", fmt), 85);
        List<Cost> currentNew = new ArrayList<>();
        currentNew.add(firstNew);
        currentNew.add(secondNew);

        //result
        Cost firstResult = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("21.01.2013 00:00:00", fmt), LocalDateTime.parse("31.01.2013 00:00:00", fmt), 90);
        Cost secondResult = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("11.01.2013 00:00:00", fmt), 80);
        Cost thirdResult = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("11.01.2013 00:00:00", fmt), LocalDateTime.parse("21.01.2013 00:00:00", fmt), 85);
        List<Cost> expect = new ArrayList<>();
        expect.add(firstResult);
        expect.add(secondResult);
        expect.add(thirdResult);
        List<Cost> result = BigCode.addNewCosts(current, currentNew);
        Assert.assertThat(result, is(expect));
    }

    @Test
    public void TestForOneCostMethod_CurrentBetweenTwoInputs() {
        //current
        Cost first = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("06.01.2013 00:00:00", fmt), 80);
        //input
        Cost firstNew = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("28.12.2012 00:00:00", fmt), LocalDateTime.parse("02.01.2013 00:00:00", fmt), 75);
        Cost secondNew = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("05.01.2013 00:00:00", fmt), LocalDateTime.parse("21.01.2013 00:00:00", fmt), 85);
        List<Cost> currentNew = new ArrayList<>();
        currentNew.add(firstNew);
        currentNew.add(secondNew);
        //result
        Cost firstResult = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("02.01.2013 00:00:00", fmt), LocalDateTime.parse("05.01.2013 00:00:00", fmt), 80);
        Cost secondResult = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("28.12.2012 00:00:00", fmt), LocalDateTime.parse("02.01.2013 00:00:00", fmt), 75);
        Cost thirdResult = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("05.01.2013 00:00:00", fmt), LocalDateTime.parse("21.01.2013 00:00:00", fmt), 85);
        List<Cost> expect = new ArrayList<>();
        expect.add(firstResult);
        expect.add(secondResult);
        expect.add(thirdResult);
        List<Cost> result = BigCode.addNewCosts(first, currentNew);
        Assert.assertThat(result, is(expect));
    }

    @Test
    public void TestForOneCostMethod_WhenProduct_codesDifferent() {
        //current
        Cost first = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("06.01.2013 00:00:00", fmt), 80);
        //input
        Cost firstNew = new Cost(0, "132213", 1, 1,
                LocalDateTime.parse("28.12.2012 00:00:00", fmt), LocalDateTime.parse("02.01.2013 00:00:00", fmt), 75);
        List<Cost> currentNew = new ArrayList<>();
        currentNew.add(firstNew);
        //result
        Cost firstResult = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("06.01.2013 00:00:00", fmt), 80);
        Cost secondResult = new Cost(0, "132213", 1, 1,
                LocalDateTime.parse("28.12.2012 00:00:00", fmt), LocalDateTime.parse("02.01.2013 00:00:00", fmt), 75);
        List<Cost> expect = new ArrayList<>();
        expect.add(firstResult);
        expect.add(secondResult);
        List<Cost> result = BigCode.addNewCosts(first, currentNew);
        Assert.assertThat(result, is(expect));
    }

    @Test
    public void TestForOneCostMethod_WhenSameExceptValues() {
        //current
        Cost first = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("06.01.2013 00:00:00", fmt), 80);
        //input
        Cost firstNew = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("06.01.2013 00:00:00", fmt), 90);
        List<Cost> currentNew = new ArrayList<>();
        currentNew.add(firstNew);
        //result
        Cost firstResult = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("06.01.2013 00:00:00", fmt), 90);
        List<Cost> expect = new ArrayList<>();
        expect.add(firstResult);
        List<Cost> result = BigCode.addNewCosts(first, currentNew);
        Assert.assertThat(result, is(expect));
    }

    @Test
    public void TestJoin_WhenSameExceptValues() {
        //current
        Cost first = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("06.01.2013 00:00:00", fmt), 80);
        //input
        Cost firstNew = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("06.01.2013 00:00:00", fmt), 90);
        //result
        Cost firstResult = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("06.01.2013 00:00:00", fmt), 90);
        List<Cost> expect = new ArrayList<>();
        expect.add(firstResult);
        List<Cost> result = BigCode.join(first, firstNew);
        Assert.assertThat(result, is(expect));
    }

    @Test
    public void TestJoin_WhenProduct_codesDifferent() {
        //current
        Cost first = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("06.01.2013 00:00:00", fmt), 80);
        //input
        Cost firstNew = new Cost(0, "132213", 1, 1,
                LocalDateTime.parse("28.12.2012 00:00:00", fmt), LocalDateTime.parse("02.01.2013 00:00:00", fmt), 75);
        //result
        Cost firstResult = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("06.01.2013 00:00:00", fmt), 80);
        Cost secondResult = new Cost(0, "132213", 1, 1,
                LocalDateTime.parse("28.12.2012 00:00:00", fmt), LocalDateTime.parse("02.01.2013 00:00:00", fmt), 75);
        List<Cost> expect = new ArrayList<>();
        expect.add(firstResult);
        expect.add(secondResult);
        List<Cost> result = BigCode.join(first, firstNew);
        Assert.assertThat(result, is(expect));
    }

    @Test
    public void TestJoin_WhenSameValues1() {
        //current
        Cost first = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("16.01.2013 00:00:00", fmt), 80);
        //input
        Cost firstNew = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("10.01.2013 00:00:00", fmt), LocalDateTime.parse("01.02.2013 00:00:00", fmt), 80);
        //result
        Cost firstResult = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("01.02.2013 00:00:00", fmt), 80);
        List<Cost> expect = new ArrayList<>();
        expect.add(firstResult);
        List<Cost> result = BigCode.join(first, firstNew);
        Assert.assertThat(result, is(expect));
    }

    @Test
    public void TestJoin_WhenDifferentValues() {
        //current
        Cost first = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("16.01.2013 00:00:00", fmt), 80);
        //input
        Cost firstNew = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("10.01.2013 00:00:00", fmt), LocalDateTime.parse("01.02.2013 00:00:00", fmt), 100);
        //result
        Cost firstResult = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("10.01.2013 00:00:00", fmt), 80);
        Cost secondResult = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("10.01.2013 00:00:00", fmt), LocalDateTime.parse("01.02.2013 00:00:00", fmt), 100);
        List<Cost> expect = new ArrayList<>();
        expect.add(firstResult);
        expect.add(secondResult);
        List<Cost> result = BigCode.join(first, firstNew);
        Assert.assertThat(result, is(expect));
    }

    @Test
    public void TestJoin_WhenBetweenAndSameValues() {
        //current
        Cost first = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("16.01.2013 00:00:00", fmt), 80);
        //input
        Cost firstNew = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("03.01.2013 00:00:00", fmt), LocalDateTime.parse("06.01.2013 00:00:00", fmt), 80);
        //result
        Cost firstResult = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("16.01.2013 00:00:00", fmt), 80);
        List<Cost> expect = new ArrayList<>();
        expect.add(firstResult);
        List<Cost> result = BigCode.join(first, firstNew);
        Assert.assertThat(result, is(expect));
    }
}