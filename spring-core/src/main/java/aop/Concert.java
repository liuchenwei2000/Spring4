package aop;

/**
 * 演奏会
 * <p>
 *     一个 Performance 接口的具体实现类
 * <p>
 * Created by liuchenwei on 2016/12/5.
 */
public class Concert implements Performance {

    @Override
    public void perform() {
        System.out.println("3 3 4 5 丨5 4 3 2 丨1 1 2 3丨");
        System.out.println("3 .2 2 —丨3 3 4 5 丨5 4 3 2 丨");
        System.out.println("1 1 2 3丨2 .1 1 —丨2 2 3 1 丨2 3 4 3");
    }
}
