package hello.aop.example;

import hello.aop.example.annotation.Retry;
import hello.aop.example.annotation.Trace;
import org.springframework.stereotype.Repository;

@Repository
public class ExamRepository {
    private static int seq  = 0;

    /*
    5번에 1번 실패하는 요청
     */
    @Trace
    @Retry(4)
    public String save(String itemId) {
        seq++;
        if(seq%5 == 0) {
            throw new IllegalStateException("exception");
        }
        return "ok";
    }
}
