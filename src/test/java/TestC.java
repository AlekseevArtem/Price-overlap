
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
        List<Cost> current = new ArrayList<>(
                List.of(
                        new Cost (0, "122856", 1, 1,
                                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("31.01.2013 23:59:59", fmt), 11000),
                        new Cost (0, "122856", 2, 1,
                                LocalDateTime.parse("10.01.2013 00:00:00", fmt), LocalDateTime.parse("20.01.2013 23:59:59", fmt), 99000),
                        new Cost (0, "6654", 1, 2,
                                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("31.01.2013 00:00:00", fmt), 5000)
                ));

        List<Cost> input = new ArrayList<> (
                List.of(
                        new Cost (0, "122856", 1, 1,
                                LocalDateTime.parse("20.01.2013 00:00:00", fmt), LocalDateTime.parse("20.02.2013 23:59:59", fmt), 11000),
                        new Cost (0, "122856", 2, 1,
                                LocalDateTime.parse("15.01.2013 00:00:00", fmt), LocalDateTime.parse("25.01.2013 23:59:59", fmt), 92000),
                        new Cost (0, "6654", 1, 2,
                                LocalDateTime.parse("12.01.2013 00:00:00", fmt), LocalDateTime.parse("13.01.2013 00:00:00", fmt), 4000)
                )
        );
        List<Cost> expect = new ArrayList<>(
                List.of(
                        new Cost (0, "122856", 2, 1,
                                LocalDateTime.parse("10.01.2013 00:00:00", fmt), LocalDateTime.parse("15.01.2013 00:00:00", fmt), 99000),
                        new Cost (0, "6654", 1, 2,
                                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("12.01.2013 00:00:00", fmt), 5000),
                        new Cost (0, "6654", 1, 2,
                                LocalDateTime.parse("12.01.2013 00:00:00", fmt), LocalDateTime.parse("13.01.2013 00:00:00", fmt), 4000),
                        new Cost (0, "6654", 1, 2,
                                LocalDateTime.parse("13.01.2013 00:00:00", fmt), LocalDateTime.parse("31.01.2013 00:00:00", fmt), 5000),
                        new Cost (0, "122856", 1, 1,
                                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("20.02.2013 23:59:59", fmt), 11000),
                        new Cost (0, "122856", 2, 1,
                                LocalDateTime.parse("15.01.2013 00:00:00", fmt), LocalDateTime.parse("25.01.2013 23:59:59", fmt), 92000)
                )
        );
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
        //expect
        List<Cost> expect = new ArrayList<>(
                List.of(
                        new Cost(0, "2345", 1, 1,
                                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("05.01.2013 00:00:00", fmt), 80),
                        new Cost(0, "2345", 1, 1,
                                LocalDateTime.parse("05.01.2013 00:00:00", fmt), LocalDateTime.parse("16.01.2013 00:00:00", fmt), 90),
                        new Cost(0, "2345", 1, 1,
                                LocalDateTime.parse("16.01.2013 00:00:00", fmt), LocalDateTime.parse("28.01.2013 00:00:00", fmt), 80)
                )
        );
        List<Cost> result = BigCode.addNewCosts(first, currentNew);
        Assert.assertThat(result, is(expect));
    }

    @Test
    public void TestFromExamples2() {
        //current
        List<Cost> current = new ArrayList<>(
                List.of(
                        new Cost(0, "2345", 1, 1,
                                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("16.01.2013 00:00:00", fmt), 100),
                        new Cost(0, "2345", 1, 1,
                                LocalDateTime.parse("16.01.2013 00:00:00", fmt), LocalDateTime.parse("31.01.2013 00:00:00", fmt), 120)
                )
        );
        //input
        Cost firstNew = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("07.01.2013 00:00:00", fmt), LocalDateTime.parse("24.01.2013 00:00:00", fmt), 110);
        List<Cost> currentNew = new ArrayList<>();
        currentNew.add(firstNew);
        //expect
        List<Cost> expect = new ArrayList<>(
                List.of(
                        new Cost(0, "2345", 1, 1,
                                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("07.01.2013 00:00:00", fmt), 100),
                        new Cost(0, "2345", 1, 1,
                                LocalDateTime.parse("24.01.2013 00:00:00", fmt), LocalDateTime.parse("31.01.2013 00:00:00", fmt), 120),
                        new Cost(0, "2345", 1, 1,
                                LocalDateTime.parse("07.01.2013 00:00:00", fmt), LocalDateTime.parse("24.01.2013 00:00:00", fmt), 110)
                        )
        );
        List<Cost> result = BigCode.addNewCosts(current, currentNew);
        Assert.assertThat(result, is(expect));
    }

    @Test
    public void TestFromExamples3() {
        //current
        List<Cost> current = new ArrayList<>(
                List.of(
                        new Cost(0, "2345", 1, 1,
                                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("06.01.2013 00:00:00", fmt), 80),
                        new Cost(0, "2345", 1, 1,
                                LocalDateTime.parse("06.01.2013 00:00:00", fmt), LocalDateTime.parse("16.01.2013 00:00:00", fmt), 87),
                        new Cost(0, "2345", 1, 1,
                                LocalDateTime.parse("16.01.2013 00:00:00", fmt), LocalDateTime.parse("31.01.2013 00:00:00", fmt), 90)
                )
        );
        //input
        List<Cost> input = new ArrayList<>(
                List.of(
                        new Cost(0, "2345", 1, 1,
                                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("11.01.2013 00:00:00", fmt), 80),
                        new Cost(0, "2345", 1, 1,
                                LocalDateTime.parse("11.01.2013 00:00:00", fmt), LocalDateTime.parse("21.01.2013 00:00:00", fmt), 85)
                )
        );
        //expect
        List<Cost> expect = new ArrayList<>(
                List.of(
                        new Cost(0, "2345", 1, 1,
                                LocalDateTime.parse("21.01.2013 00:00:00", fmt), LocalDateTime.parse("31.01.2013 00:00:00", fmt), 90),
                        new Cost(0, "2345", 1, 1,
                                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("11.01.2013 00:00:00", fmt), 80),
                        new Cost(0, "2345", 1, 1,
                                LocalDateTime.parse("11.01.2013 00:00:00", fmt), LocalDateTime.parse("21.01.2013 00:00:00", fmt), 85)

                )
        );
        List<Cost> result = BigCode.addNewCosts(current, input);
        Assert.assertThat(result, is(expect));
    }

    @Test
    public void TestForOneCostMethod_CurrentBetweenTwoInputs() {
        //current
        Cost first = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("06.01.2013 00:00:00", fmt), 80);
        //input
        List<Cost> input = new ArrayList<>(
                List.of(
                        new Cost(0, "2345", 1, 1,
                                LocalDateTime.parse("28.12.2012 00:00:00", fmt), LocalDateTime.parse("02.01.2013 00:00:00", fmt), 75),
                        new Cost(0, "2345", 1, 1,
                                LocalDateTime.parse("05.01.2013 00:00:00", fmt), LocalDateTime.parse("21.01.2013 00:00:00", fmt), 85)
                )
        );
        //expect
        List<Cost> expect = new ArrayList<>(
                List.of(
                        new Cost(0, "2345", 1, 1,
                                LocalDateTime.parse("02.01.2013 00:00:00", fmt), LocalDateTime.parse("05.01.2013 00:00:00", fmt), 80),
                        new Cost(0, "2345", 1, 1,
                                LocalDateTime.parse("28.12.2012 00:00:00", fmt), LocalDateTime.parse("02.01.2013 00:00:00", fmt), 75),
                        new Cost(0, "2345", 1, 1,
                                LocalDateTime.parse("05.01.2013 00:00:00", fmt), LocalDateTime.parse("21.01.2013 00:00:00", fmt), 85)
                )
        );
        List<Cost> result = BigCode.addNewCosts(first, input);
        Assert.assertThat(result, is(expect));
    }

    @Test
    public void TestForOneCostMethod_WhenProduct_codesDifferent() {
        //current
        Cost first = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("06.01.2013 00:00:00", fmt), 80);
        //input
        List<Cost> input = new ArrayList<>(
                List.of(
                        new Cost(0, "132213", 1, 1,
                                LocalDateTime.parse("28.12.2012 00:00:00", fmt), LocalDateTime.parse("02.01.2013 00:00:00", fmt), 75)
                )
        );
        //expect
        List<Cost> expect = new ArrayList<>(
                List.of(
                        new Cost(0, "2345", 1, 1,
                                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("06.01.2013 00:00:00", fmt), 80),
                        new Cost(0, "132213", 1, 1,
                                LocalDateTime.parse("28.12.2012 00:00:00", fmt), LocalDateTime.parse("02.01.2013 00:00:00", fmt), 75)
                )
        );
        List<Cost> result = BigCode.addNewCosts(first, input);
        Assert.assertThat(result, is(expect));
    }

    @Test
    public void TestForOneCostMethod_WhenSameExceptValues() {
        //current
        Cost first = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("06.01.2013 00:00:00", fmt), 80);
        //input
        List<Cost> input = new ArrayList<>(
                List.of(
                        new Cost(0, "2345", 1, 1,
                                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("06.01.2013 00:00:00", fmt), 90)
                )
        );
        //expect
        List<Cost> expect = new ArrayList<>(
                List.of(
                        new Cost(0, "2345", 1, 1,
                                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("06.01.2013 00:00:00", fmt), 90)
                )
        );
        List<Cost> result = BigCode.addNewCosts(first, input);
        Assert.assertThat(result, is(expect));
    }

    @Test
    public void TestTwoCost_WhenSameExceptValues() {
        //current
        Cost first = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("06.01.2013 00:00:00", fmt), 80);
        //input
        Cost firstNew = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("06.01.2013 00:00:00", fmt), 90);
        //expect
        List<Cost> expect = new ArrayList<>(
                List.of(
                        new Cost(0, "2345", 1, 1,
                                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("06.01.2013 00:00:00", fmt), 90)
                )
        );
        List<Cost> result = BigCode.addNewCosts(first, firstNew);
        Assert.assertThat(result, is(expect));
    }

    @Test
    public void TestTwoCost_WhenProduct_codesDifferent() {
        //current
        Cost first = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("06.01.2013 00:00:00", fmt), 80);
        //input
        Cost firstNew = new Cost(0, "132213", 1, 1,
                LocalDateTime.parse("28.12.2012 00:00:00", fmt), LocalDateTime.parse("02.01.2013 00:00:00", fmt), 75);
        //expect
        List<Cost> expect = new ArrayList<>(
                List.of(
                        new Cost(0, "2345", 1, 1,
                                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("06.01.2013 00:00:00", fmt), 80),
                        new Cost(0, "132213", 1, 1,
                                LocalDateTime.parse("28.12.2012 00:00:00", fmt), LocalDateTime.parse("02.01.2013 00:00:00", fmt), 75)

                )
        );
        List<Cost> result = BigCode.addNewCosts(first, firstNew);
        Assert.assertThat(result, is(expect));
    }

    @Test
    public void TestTwoCost_WhenSameValues1() {
        //current
        Cost first = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("16.01.2013 00:00:00", fmt), 80);
        //input
        Cost firstNew = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("10.01.2013 00:00:00", fmt), LocalDateTime.parse("01.02.2013 00:00:00", fmt), 80);
        //expect
        List<Cost> expect = new ArrayList<>(
                List.of(
                        new Cost(0, "2345", 1, 1,
                                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("01.02.2013 00:00:00", fmt), 80)
                )
        );
        List<Cost> result = BigCode.addNewCosts(first, firstNew);
        Assert.assertThat(result, is(expect));
    }

    @Test
    public void TestTwoCost_WhenDifferentValues() {
        //current
        Cost first = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("16.01.2013 00:00:00", fmt), 80);
        //input
        Cost firstNew = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("10.01.2013 00:00:00", fmt), LocalDateTime.parse("01.02.2013 00:00:00", fmt), 100);
        //expect
        List<Cost> expect = new ArrayList<>(
                List.of(
                        new Cost(0, "2345", 1, 1,
                                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("10.01.2013 00:00:00", fmt), 80),
                        new Cost(0, "2345", 1, 1,
                                LocalDateTime.parse("10.01.2013 00:00:00", fmt), LocalDateTime.parse("01.02.2013 00:00:00", fmt), 100)
                )
        );
        List<Cost> result = BigCode.addNewCosts(first, firstNew);
        Assert.assertThat(result, is(expect));
    }

    @Test
    public void TestTwoCost_WhenBetweenAndSameValues() {
        //current
        Cost first = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("16.01.2013 00:00:00", fmt), 80);
        //input
        Cost firstNew = new Cost(0, "2345", 1, 1,
                LocalDateTime.parse("03.01.2013 00:00:00", fmt), LocalDateTime.parse("06.01.2013 00:00:00", fmt), 80);
        //expect
        List<Cost> expect = new ArrayList<>(
                List.of(
                        new Cost(0, "2345", 1, 1,
                                LocalDateTime.parse("01.01.2013 00:00:00", fmt), LocalDateTime.parse("16.01.2013 00:00:00", fmt), 80)
                )
        );
        List<Cost> result = BigCode.addNewCosts(first, firstNew);
        Assert.assertThat(result, is(expect));
    }
}