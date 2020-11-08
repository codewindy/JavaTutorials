import cn.hutool.core.util.StrUtil;

import java.util.Comparator;
import java.util.stream.Stream;

/**
 * @author codewindy
 * @date 2020-11-08 5:49 PM
 * @since 1.0.0
 */
public class TestLamdaDebugger {
    public static void main(String[] args) {
        String[] str ={"2","12","79",null,"36","84","84"};
        int sum = Stream.of(str).filter(e -> StrUtil.isNotBlank(e)).distinct().mapToInt(e -> Integer.parseInt(e)).sum();
        System.out.println("sum = " + sum);

    }
}