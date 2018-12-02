package util;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author dmf
 * @date 2018/1/15
 * 
 * 统计代码行数的工具类
 */
public class CodeCount {

    public static <T> Consumer<T> cof(UncheckedConsumer<T> mapper) {
        Objects.requireNonNull(mapper);
        return t -> {
            try {
                mapper.accept(t);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    public static <T> Consumer<T> ncof(UncheckedConsumer<T> mapper) {
        Objects.requireNonNull(mapper);
        return t -> {
            try {
                mapper.accept(t);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        };
    }

    public static <T, R> Function<T, R> of(UncheckedFunction<T, R> mapper) {
        Objects.requireNonNull(mapper);
        return t -> {
            try {
                return mapper.apply(t);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    public static <T, R> Function<T, R> eof(UncheckedFunction<T, R> mapper, Exception cex) {
        Objects.requireNonNull(mapper);
        return t -> {
            try {
                return mapper.apply(t);
            } catch (Exception ex) {
                if(cex!=null&&cex.getClass()==ex.getClass()){
                    throw new RuntimeException(cex);
                }else{
                    throw new RuntimeException(ex);
                }
            }
        };
    }

    public static <T, R> Function<T, R> of(UncheckedFunction<T, R> mapper, R defaultR) {
        Objects.requireNonNull(mapper);
        return t -> {
            try {
                return mapper.apply(t);
            } catch (Exception ex) {
                ex.printStackTrace(System.err);
                return defaultR;
            }
        };
    }

    @FunctionalInterface
    public static interface UncheckedFunction<T, R> {
        R apply(T t) throws Exception;
    }

    @FunctionalInterface
    public static interface UncheckedConsumer<T> {
        void accept(T t) throws Exception;
    }

    public static void main(String[] args) throws IOException {
        /**
         * 1.递归获取目录流,
         * 2.筛选出文件,然后筛选出java文件
         * 3.将会抛出受检异常的Lambda包装为抛出非受检异常的Lambda
         * 4.过滤掉空行，以//的注释
         * 过滤掉以/*、/**开头，/*、/**相应结尾的注释，注意有可能不在同一行
         * 过滤掉以/*、/**的注释,不是以/*、/**结尾的注释
         * 过滤掉不是以/*、/**的注释,是以/*、/**结尾的注释
         * 过滤掉*的注释，@Override注解
         */

        long count = Files.walk(Paths.get("E:\\java-project\\my-project\\javabase\\base\\src\\main\\java\\jdk\\IntMapTest.java"))
                .filter(file -> !Files.isDirectory(file))
                .filter(file -> file.toString().endsWith(".java"))
                .flatMap(CodeCount.of(Files::lines, Stream.empty()))
                .filter(line -> !line.trim().isEmpty())
                .filter(line -> !line.trim().startsWith("//"))
                .filter(line -> !(line.trim().startsWith("/*") && line.trim().endsWith("*/")))
                .filter(line -> !(line.trim().startsWith("/**") && line.trim().endsWith("*/")))
                .filter(line -> !(line.trim().startsWith("/*") && !line.trim().endsWith("*/")))
                .filter(line -> !(line.trim().startsWith("/**") && !line.trim().endsWith("*/")))
                .filter(line -> !(!line.trim().startsWith("/*") && line.trim().endsWith("*/")))
                .filter(line -> !(!line.trim().startsWith("/**") && line.trim().endsWith("*/")))
                .filter(line -> !line.trim().startsWith("*"))
                .filter(line -> !line.trim().startsWith("@Override"))
                .count();

        System.out.println("代码行数："+count);

    }
}
