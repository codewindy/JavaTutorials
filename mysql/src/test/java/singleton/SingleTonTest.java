package singleton;

/**
 * 饿汉式单例模式
 */
public class SingleTonTest {
    public static SingleTonTest instance = new SingleTonTest();

    private SingleTonTest() {

    }
    public static SingleTonTest getSingleTonTest(){
        return instance;
    }
}
