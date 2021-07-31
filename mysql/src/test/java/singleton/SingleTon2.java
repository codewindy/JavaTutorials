package singleton;

public class SingleTon2 {

    private  static  volatile SingleTon2 instance = null;

    //私有化构造器
    private SingleTon2(){};

    public static SingleTon2 getInstance(){
        if (instance == null) {
            synchronized (SingleTon2.class){
                instance = new SingleTon2();
            }
        }
        return  instance;

    }
}
