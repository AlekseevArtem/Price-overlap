import java.time.LocalDateTime;
import java.util.*;

public class BigCode {
    /**
     * Метод объединения имеющихся цен с импортированными из внешней системы.
     *
     * @return объединенная коллекция.
     */
    public static List<Cost> addNewCosts(List<Cost> current, List<Cost> input) {
        List<Cost> result = new ArrayList<>(current);
        for (Cost inCost : input) {       //цикл по входящей коллекции
            boolean needToAdd = false;
            LocalDateTime beginIn = inCost.getBegin();
            LocalDateTime endIn = inCost.getEnd();
            long valueIn = inCost.getValue();
            for (Cost CurCost : current) {          //цикл по исходной коллекции
                LocalDateTime beginCur = CurCost.getBegin();
                LocalDateTime endCur = CurCost.getEnd();
                long valueCur = CurCost.getValue();
                if (CurCost.getProduct_code().equals(inCost.getProduct_code())               //нас интересуют только цены с одинаковыми product_code, number и depart
                        && CurCost.getDepart() == inCost.getDepart()
                        && CurCost.getNumber() == inCost.getNumber()) {
                    if (beginIn.compareTo(beginCur) > 0 && endIn.compareTo(endCur) < 0) {    //Если in вписывается в cur
                        if (valueCur != valueIn) {               //и у них разные value, то получаем три объекта
                            int index = result.indexOf(CurCost);
                            result.remove(CurCost);
                            Cost first = new Cost(CurCost);
                            Cost third = new Cost(CurCost);
                            third.setBegin(endIn);
                            result.add(index, third);
                            result.add(index, inCost);
                            first.setEnd(beginIn);
                            result.add(index, first);
                        }
                        needToAdd = true;
                        break;                                  //и у них одинаковые value, то ничего не изменяется. И объект inCost не добавляем в коллекцию
                    } else if (beginIn.compareTo(beginCur) <= 0 && endIn.compareTo(endCur) >= 0) {     //Если cur полностью перекрывается in, то cur удаляется.
                        result.remove(CurCost);
                        if (endIn.compareTo(endCur) == 0) {                     //Если in дата окончания равна дате окончания cur то добавляем inCost
                            result.add(inCost);
                            needToAdd = true;
                            break;
                        }
                    } else if (beginIn.compareTo(beginCur) > 0 && endIn.compareTo(endCur) >= 0) {     //Если cur перекрывается в задней части
                        if (endIn.compareTo(endCur) == 0) {                          //и End даты равны
                            if (valueCur != valueIn) {       //и у них разные value, то добавляем inCost и изменяем curCost
                                CurCost.setEnd(endIn);
                                result.add(inCost);
                            }                                //а если у них одинаковые value, то ничего не изменяется. И объект inCost не добавляем в коллекцию
                            needToAdd = true;
                            break;
                        } else {                                                     //и End даты не равны
                            if (valueCur != valueIn) {       //и у них разные value, то изменяем curCost
                                CurCost.setEnd(beginIn);
                            } else {                         //и у них одинаковые value, то удаляем CurCost и добавляем его дату к InCost
                                beginIn = beginCur;
                                inCost.setBegin(beginIn);
                                result.remove(CurCost);
                            }
                        }
                    } else if (beginIn.compareTo(beginCur) <= 0 && endIn.compareTo(endCur) < 0) {      //Если cur перекрывается в передней части
                        if (valueCur != valueIn) {       //и у них разные value, то добавляем InCost и изменяем CurCost
                            result.add(inCost);
                            CurCost.setBegin(endIn);
                        } else {                         //и у них одинаковые value, то удаляем CurCost и добавляем его дату к InCost
                            endIn = endCur;
                            result.remove(CurCost);
                            inCost.setEnd(endIn);
                            result.add(inCost);
                        }
                        needToAdd = true;
                        break;
                    } else System.out.println("Это мой код и если вы это прочитали то он плох");
                }
            }
            if (!needToAdd) {
                result.add(inCost);
            }
        }
        return result;
    }

    public static List<Cost> addNewCosts(Cost CurCost, List<Cost> input) {
        List<Cost> result = new ArrayList<>();
        result.add(CurCost);
        for (Cost inCost : input) {       //цикл по входящей коллекции
            LocalDateTime beginIn = inCost.getBegin();
            LocalDateTime endIn = inCost.getEnd();
            long valueIn = inCost.getValue();
            LocalDateTime beginCur = CurCost.getBegin();
            LocalDateTime endCur = CurCost.getEnd();
            long valueCur = CurCost.getValue();
            if (CurCost.getProduct_code().equals(inCost.getProduct_code())               //нас интересуют только цены с одинаковыми product_code, number и depart
                    && CurCost.getDepart() == inCost.getDepart()
                    && CurCost.getNumber() == inCost.getNumber()) {
                if (beginIn.compareTo(beginCur) > 0 && endIn.compareTo(endCur) < 0 && valueCur != valueIn) {    //Если in вписывается в cur и у них разные value, то получаем три объекта
                    int index = result.indexOf(CurCost);
                    result.remove(CurCost);
                    Cost first = new Cost(CurCost);
                    Cost third = new Cost(CurCost);
                    third.setBegin(endIn);
                    result.add(index, third);
                    result.add(index, inCost);
                    first.setEnd(beginIn);
                    result.add(index, first);
                    } else if (beginIn.compareTo(beginCur) <= 0 && endIn.compareTo(endCur) >= 0) {     //Если cur полностью перекрывается in, то cur удаляется.
                        result.remove(CurCost);
                        if (endIn.compareTo(endCur) == 0) {                     //Если in дата окончания равна дате окончания cur то добавляем inCost
                            result.add(inCost);
                        }
                    } else if (beginIn.compareTo(beginCur) > 0 && endIn.compareTo(endCur) >= 0) {     //Если cur перекрывается в задней части
                        if (endIn.compareTo(endCur) == 0) {                          //и End даты равны
                            if (valueCur != valueIn) {       //и у них разные value, то добавляем inCost и изменяем curCost
                                CurCost.setEnd(endIn);
                                result.add(inCost);
                            }                                //а если у них одинаковые value, то ничего не изменяется. И объект inCost не добавляем в коллекцию
                        } else {                                                     //и End даты не равны
                            if (valueCur != valueIn) {       //и у них разные value, то изменяем curCost и добавляем inCost
                                CurCost.setEnd(beginIn);
                                result.add(inCost);
                            } else {                         //и у них одинаковые value, то удаляем CurCost и добавляем его дату к InCost
                                beginIn = beginCur;
                                inCost.setBegin(beginIn);
                                result.remove(CurCost);
                            }
                        }
                    } else if (beginIn.compareTo(beginCur) <= 0 && endIn.compareTo(endCur) < 0) {      //Если cur перекрывается в передней части
                        if (valueCur != valueIn) {       //и у них разные value, то добавляем InCost и изменяем CurCost
                            result.add(inCost);
                            CurCost.setBegin(endIn);
                        } else {                         //и у них одинаковые value, то удаляем CurCost и добавляем его дату к InCost
                            endIn = endCur;
                            result.remove(CurCost);
                            inCost.setEnd(endIn);
                            result.add(inCost);
                        }
                    } else System.out.println("Это мой код и если вы это прочитали то он плох");
                } else {result.add(inCost);}
        }
        return result;
    }

    public static List<Cost> join(Cost CurCost, Cost InCost) {
        List<Cost> result = new ArrayList<>();
        result.add(CurCost);
        LocalDateTime beginIn = InCost.getBegin();
        LocalDateTime endIn = InCost.getEnd();
        long valueIn = InCost.getValue();
        LocalDateTime beginCur = CurCost.getBegin();
        LocalDateTime endCur = CurCost.getEnd();
        long valueCur = CurCost.getValue();
        if (CurCost.getProduct_code().equals(InCost.getProduct_code())               //нас интересуют только цены с одинаковыми product_code, number и depart
                && CurCost.getDepart() == InCost.getDepart()
                && CurCost.getNumber() == InCost.getNumber()) {
            if (beginIn.compareTo(beginCur) > 0 && endIn.compareTo(endCur) < 0 && valueCur != valueIn) {    //Если in вписывается в cur и у них разные value, то получаем три объекта
                int index = result.indexOf(CurCost);
                result.remove(CurCost);
                Cost first = new Cost(CurCost);
                Cost third = new Cost(CurCost);
                third.setBegin(endIn);
                result.add(index, third);
                result.add(index, InCost);
                first.setEnd(beginIn);
                result.add(index, first);
            } else if (beginIn.compareTo(beginCur) <= 0 && endIn.compareTo(endCur) >= 0) {     //Если cur полностью перекрывается in, то cur удаляется.
                result.remove(CurCost);
                if (endIn.compareTo(endCur) == 0) {                     //Если in дата окончания равна дате окончания cur то добавляем inCost
                    result.add(InCost);
                }
            } else if (beginIn.compareTo(beginCur) > 0 && endIn.compareTo(endCur) >= 0) {     //Если cur перекрывается в задней части
                if (endIn.compareTo(endCur) == 0) {                          //и End даты равны
                    if (valueCur != valueIn) {       //и у них разные value, то добавляем inCost и изменяем curCost
                        CurCost.setEnd(endIn);
                        result.add(InCost);
                    }                                //а если у них одинаковые value, то ничего не изменяется. И объект inCost не добавляем в коллекцию
                } else {                                                     //и End даты не равны
                    if (valueCur != valueIn) {       //и у них разные value, то изменяем curCost и добавляем inCost
                        CurCost.setEnd(beginIn);
                        result.add(InCost);
                    } else {                         //и у них одинаковые value, то удаляем CurCost и добавляем его дату к InCost
                        beginIn = beginCur;
                        InCost.setBegin(beginIn);
                        result.remove(CurCost);
                    }
                }
            } else if (beginIn.compareTo(beginCur) <= 0 && endIn.compareTo(endCur) < 0) {      //Если cur перекрывается в передней части
                if (valueCur != valueIn) {       //и у них разные value, то добавляем InCost и изменяем CurCost
                    result.add(InCost);
                    CurCost.setBegin(endIn);
                } else {                         //и у них одинаковые value, то удаляем CurCost и добавляем его дату к InCost
                    endIn = endCur;
                    result.remove(CurCost);
                    InCost.setEnd(endIn);
                    result.add(InCost);
                }
            } else System.out.println("Это мой код и если вы это прочитали то он плох");
        } else {result.add(InCost);}
        return result;
    }
}
