package java8;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Set;

/**
 * @author dmf
 * @date 2018/1/17
 *
 * java8日期api的简单介绍：
 *      java.time包下：
 *          日期(date)，时间(time)，日期/时间(datetime)，时区（zone)，时刻（instant），间隔（duration）与时钟（clock）
 */
public class DateApi2Test {


    /**
     * 测试日期和时间
     * 日期（年月日）对应的是java.time.LocalDate
     * 时间（时分秒）对应的是java.time.LocalTime
     * 日期时间（年月日时分秒）对应的是java.time.LocalDateTime
     */
    @Test
    public void testDate(){
        //获取当前时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        //将当前日期减去两天
        LocalDateTime time2 = now.minusDays(2);
        System.out.println(time2);

        //将当前日期加上五天
        LocalDateTime time3 = now.plusDays(5);
        System.out.println(time3);

        //输出当前年份
        System.out.println(now.getYear());

        //构造一个指定的日期对象
        LocalDateTime time4 = LocalDateTime.of(2017, 1, 24, 8, 24);
        System.out.println(time4);
    }

    /**
     * 测试时间戳
     * 时间戳对应的是java.time.Instant
     */
    @Test
    public void testInstance(){
        Instant now = Instant.now();
        System.out.println(now);

        //获取偏移的时间
        OffsetDateTime atOffset = now.atOffset(ZoneOffset.ofHours(8));
        System.out.println(atOffset);

        //转换成毫秒，System.currentTimeMillis()是一样的
        long start = now.getEpochSecond();
        System.out.println(start);
    }

    /**
     * 测试时间间隔
     * java.time.Duration用于计算两个“时间”间隔
     java.time.Period用于计算两个“日期”间隔
     */
    @Test
    public void testDistance() throws InterruptedException {
        LocalTime start = LocalTime.now();
        Thread.sleep(3000);
        LocalTime end = LocalTime.now();
        Duration between = Duration.between(start, end);
        System.out.println(between);
    }

    @Test
    public void testDistance2() throws InterruptedException {
        LocalDate start = LocalDate.of(2017,6,18);
        LocalDate end = LocalDate.of(2017,6,20);
        Period between = Period.between(start, end);
        System.out.println(between);
    }

    /**
     * 日期格式化的类是java.text.SimpleDateFormat
     * Java 8 提供的日期格式化类是java.time.format.DateTimeFormatter
     */
    @Test
    public void testDateFormatter(){
        DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.format(formatter));

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(now.format(formatter1));

        //将字符串转化成日期
        LocalDate date = LocalDate.parse(now.format(formatter1),formatter1);
        System.out.println(date);
    }

    /**
     * 测试时区
     */
    @Test
    public void testZone(){
        Set<String> ids = ZoneId.getAvailableZoneIds();
        for (String id : ids) {
            System.out.println(id);
        }

        //获取当前时区的日期时间
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);

        //获取美国洛杉矶时区的日期时间
        ZonedDateTime USANow = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
        System.out.println(USANow);
    }

}
