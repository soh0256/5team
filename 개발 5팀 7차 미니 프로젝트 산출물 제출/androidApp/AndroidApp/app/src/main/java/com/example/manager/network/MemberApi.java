
package com.example.manager.network;



import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MemberApi {
    private static final String SERVER_BASE_URL = "http://172.30.1.38:8080/"; // TODO - IP 를 서버 PC 의 IP로 설정해주어야 함.

    private static MemberApi INSTANCE;

    public static MemberApi getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MemberApi();
        }
        return INSTANCE;
    }

    private final MemberService memberService;

    private MemberApi() {
        memberService = new Retrofit.Builder()
                .baseUrl(SERVER_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(MemberService.class);
    }

    public MemberService getMemberService() {
        return memberService;
    }
}

