package com.gugucon.datageneration.generator;

import com.gugucon.datageneration.domain.Product;
import com.gugucon.datageneration.utils.RandomStringUtils;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class ProductGenerator {

    private final Random random = new Random();

    private final List<String> adjectives = List.of("빨간", "파란", "노란", "초록", "보라", "하얀", "샛노란", "새빨간", "검정", "크림",
                                                    "아이보리", "베이지", "차콜", "청록", "인디핑크", "카키", "핑크", "인디고블루", "예쁜",
                                                    "단정한", "멋있는", "깔끔한", "그린", "레드", "블루", "분홍", "화이트", "블랙", "스카이블루",
                                                    "하늘색", "살구색", "회색", "짙은 회색", "그레이", "다크그레이", "네이비", "남보라", "남색",
                                                    "어두운", "머스타드", "레트로한", "빈티지", "데이트룩", "남친룩", "여친룩", "어려 보이는",
                                                    "데일리룩", "코지", "꾸안꾸", "편안한", "가성비 좋은", "가성비 갑", "따뜻한", "새내기룩",
                                                    "다리가 길어보이는", "하객룩", "댄디한", "빈티지한", "소프트한", "쿨톤", "웜톤", "주황",
                                                    "새파란", "글루미", "패셔너블", "심플한", "힙한", "가벼운", "하이틴", "시그니처", "쿨한");

    private final List<String> categories = List.of("세트", "정장", "턱시도", "연미복", "프록 코트", "유니폼", "교복", "군복",
                                                  "제복", "정복", "근무복", "학교 체육복", "수영복", "비키니", "래시가드", "원피스",
                                                  "운동복", "아웃도어", "츄리닝", "한복", "바디슈트", "작업복", "커버올", "점프슈트",
                                                  "수도복", "상의", "이너", "티셔츠", "V넥 티셔츠", "긴팔 티셔츠", "폴로 셔츠",
                                                  "헨리넥 셔츠", "민소매나시", "스웨터맨투맨", "후드티", "셔츠", "와이셔츠", "블라우스",
                                                  "터틀넥", "크롭티", "핑거홀", "오프숄더", "외투", "아우터", "코트", "트렌치 코트",
                                                  "피 코트", "폴로 코트", "인버네스", "재킷", "가죽 재킷", "라이딩 재킷", "데님 자켓",
                                                  "청자켓", "테일러드 재킷", "블레이저", "블루종", "가디건", "패딩", "무스탕", "플리스",
                                                  "후드집업", "야상", "비옷우비", "윈드브레이커", "바람막이", "파카", "점퍼", "잠바",
                                                  "니트", "조끼", "볼레로", "하의", "치마", "미니스커트", "테니스 치마", "롱스커트",
                                                  "정장치마", "치마바지", "멜빵치마", "청치마", "바지", "청바지", "슬랙스", "면바지",
                                                  "치노", "반바지", "핫팬츠", "돌핀팬츠", "조거 팬츠", "스키니진", "나팔바지", "건빵바지",
                                                  "카고바지", "레깅스", "몸뻬", "양말", "스타킹", "레깅스", "속옷", "팬티", "브래지어",
                                                  "내복", "속바지", "신발", "운동화", "캔버스화", "스니커즈", "구두", "로퍼", "옥스퍼드",
                                                  "슬립온", "하이힐", "스틸레토 힐", "메리 제인", "부츠","워커", "슬리퍼", "쪼리",
                                                  "샌들", "뮬", "어글리 슈즈", "지갑", "모자", "허리띠", "벨트", "넥타이", "장갑",
                                                  "목도리", "마스크", "귀도리", "손수건", "안경");

    public List<Product> generate(final int number) {
        return IntStream.range(0, number)
                        .mapToObj(i -> Product.builder()
                                              .name(nameGenerate())
                                              .price(random.nextInt(10, 100) * 1000L)
                                              .imageFileName("/default.jpg")
                                              .stock(random.nextInt(1000))
                                              .description(RandomStringUtils.randomAlphabetic(32))
                                              .build())
                        .toList();
    }

    private String nameGenerate() {
        String adjective = adjectives.get(random.nextInt(adjectives.size()));
        String category = categories.get(random.nextInt(categories.size()));
        return adjective + " " + category;
    }

}
