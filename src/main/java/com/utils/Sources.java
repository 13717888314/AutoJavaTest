package com.utils;

public interface Sources {
    String xz_test_base_url="https://gateway-service-ts.wetax.com.cn/walkerfestivalapi-service/api";

    String xz_master_base_url="https://gateway-service.wetax.com.cn/walkerfestivalapi-service/api";

    String xz_test_Authorization="bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkJFM0Q0NDE1NDE5RjA1MjdCNUJGNzIzRTZBQzMwNTUxMEE3N0QzOTYiLCJ0eXAiOiJK" +
            "V1QiLCJ4NXQiOiJ2ajFFRlVHZkJTZTF2M0ktYXNNRlVRcDMwNVkifQ.eyJuYmYiOjE2NjI0M" +
            "jYwNDYsImV4cCI6MTY2MzQyNjA0NiwiaXNzIjoiaHR0cDovLzEwLjMwLjExMi4zOSIsImF1ZCI6ImFwaTEiLCJzdW" +
            "IiOiI4MTNkMWRmZC0zMmNjLTQ2ZmQtYjZkOS1mYjcxNzBjNTEwNDQiLCJ1c2VyaWQiOiI4MTNkMW" +
            "RmZC0zMmNjLTQ2ZmQtYjZkOS1mYjcxNzBjNTEwNDQiLCJzZXNzaW9uX2tleSI6IlR0UVBtY3U3QVM0cmNBM0Iw" +
            "bkZ1R3c9PSJ9.PbdHWSxdbpix7iXUDBp3LJuxHwutZ3TQVNux-eqy2PA66TMLTR3rEVwwr7IAtcZc3cHFMUkPjNgGD" +
            "Lcu_qbSbcH9C_wLUGEyDjXNuyk3WhMjoGKUwPnVu9Q8mjAfc9k04CGE4EJRPdyvnJsYAGgGbMDs5WZz9LqV4mNIe87fmCkS" +
            "zwwqvreDgE5dbfkM7a44wmbkArCSVV-Y9XwOSiidAMwMW1KTewhg7yw4xFiOOhPvv4GONqClYvggBDffXPXUAlDoLI-etRoWh6vNL" +
            "tQGO3y7rzBwo4aWpdFVxDPcDLdg4aGg3geseztG-vWg-79qAtnxRBCN4ZmtOXXs118AfQ";

    String xz_master_Authorization="bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkJFM0Q0NDE1NDE5RjA1MjdCNUJGNzIzRTZBQzMwNTUxMEE3N0QzOTYiLCJ0eXAiOiJKV1QiLCJ4NXQiOiJ2ajFFRlVHZkJTZTF2M0ktYXNNRlVRcDMwNVkifQ.eyJuYmYiOjE2NjE3NDMxOTUsImV4cCI6MTY2Mjc0MzE5NSwiaXNzIjoiaHR0cDovLzEwLjMwLjUuMTk6MzcwMDMiLCJhdWQiOiJhcGkxIiwic3ViIjoiZWRiOTlhYWYtMjc4MC00NmIzLTgzMzgtYWI3NDdkYzBjMDUxIiwidXNlcmlkIjoiZWRiOTlhYWYtMjc4MC00NmIzLTgzMzgtYWI3NDdkYzBjMDUxIiwic2Vzc2lvbl9rZXkiOiI3RGt6UUlwSkdRRFFCcGxkMDNDUkxBPT0ifQ.cowEufAuC1LS_gFcugZg0OmF_LNz6n73hZIAsqXi43ND4r24l5fxidE4YSAoMF5PxYyTyfAJ8eN-dwHPWtzlmFiSNbxZC0by9Xmq18sS8UoSSw6I-AfN8aA7-Qnt4EHo5vuinquBL78pYnTLadC2kcYrtHS663GKkHI_V5OvyQdokWxwGPD8_-ilUIyL_bzjxsvM3-RRU46RfZ9donF5aXn4eNom4iIrqKrZKbYYOdCvt3hK0xk3yI" +
            "TSf2qs2b_xLmfxsxVJ79mHcsiP7pOlDdo2nAX46NOuRYUYQmjS21uZipp2wuuUZ0oVLfqXwg4wY9lrZaGmqej2aLgT-ecO-A";

    String wns_test_base_url="https://wns-ts.wetax.com.cn/api";

    String wns_master_base_url="https://wns.wetax.com.cn/api";

    String wns_test_Authorization="";

    String wns_master_Authorization="bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkJFM0Q0NDE1NDE5RjA1MjdCNUJGNzIzRTZBQzMwNTUxMEE3N0QzOTYiLCJ0eXAiOiJKV1QiLCJ4NXQiOiJ2ajFFRlVHZkJTZTF2M0ktYXNNRlVRcDMwNVkifQ.eyJuYmYiOjE2NjI0NTUyMjAsImV4cCI6MTY2MzQ1NTIyMCwiaXNzIjoiaHR0cDovLzEwLjMwLjUuMTk6MzcwMDMiLCJhdWQiOiJhcGkxIiwic3ViIjoiNDE4N2Q3MGQtYTlkYS00NmEwLTlhMzQtYTkyMmI3MjNkZDU3IiwiQXNwTmV0LklkZW50aXR5LlNlY3VyaXR5U3RhbXAiOiI2QUpPNEYzUUpQNEI1MkZaTk9WV0lQRlRZRFA0MlZOVCIsInByZWZlcnJlZF91c2VybmFtZSI6IndldGF4MjEwNTIwMTUyMzM3MjMzNzg5MCIsIm5hbWUiOiJ3ZXRheDIxMDUyMDE1MjMzNzIzMzc4OTAiLCJwaG9uZV9udW1iZXIiOiIxMzcxNzg4ODMxNCIsInBob25lX251bWJlcl92ZXJpZmllZCI6ZmFsc2UsInVzZXJpZCI6IjQxODdkNzBkLWE5ZGEtNDZhMC05YTM0LWE5MjJiNzIzZGQ1NyIsInNlc3Npb25fa2V5IjoiV1hmQVhkcWl3V3F0a3N1bUtoM0RDdz09In0.gcwGE6Z1Fa1uQw47NZ41eQxkbcWDeh1rC-zM8tLYiEhwSqvirIFQoHxkkfmPuSPkdwpMGcR4B_OTsqrVOaqBI-1GEVnYNlZ0mrh8Kj0Ct4eCri2jSv7OBDo3E312XjxVpXy6mkpdEd9XLjn2aWT41YcjPpIyYKBv3MtoTIjzcmMW55NgpvNTXJG_er-eRZSGzK9uuzuA0h0u3KRsRugZQEqzAFY-HU0EgiMMMcB91icDqfA-S78mzAs6b19" +
            "NJ9elObCU4Imv9hUGOcfHJqxdGrX4oeiyi90nrcvy0X_MZs5DNIPRoMfe73Gq6iSNEtlI9kSz8if49UDQJ5BgDdkriw";

    String staffing_test_base_url="https://staffing-ts.wetax.com.cn/webapp/api";

    String staffing_master_base_url="https://staffing.wetax.com.cn/webapp/api";

    String staffing_test_Authorization="";

    String staffing_master_Authorization="bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkJFM0Q0NDE1NDE5RjA1MjdCNUJGNzIzRTZBQzMwNTUxMEE3N0QzOTYiLCJ0eXAiOiJKV1QiLCJ4NXQiOiJ2ajFFRlVHZkJTZTF2M0ktYXNNRlVRcDMwNVkifQ.eyJuYmYiOjE2NjI1MTQxNjMsImV4cCI6MTY2MzUxNDE2MywiaXNzIjoiaHR0cDovLzEwLjMwLjUuMTk6MzcwMDMiLCJhdWQiOiJhcGkxIiwic3ViIjoiNDE4N2Q3MGQtYTlkYS00NmEwLTlhMzQtYTkyMmI3MjNkZDU3IiwiQXNwTmV0LklkZW50aXR5LlNlY3VyaXR5U3RhbXAiOiI2QUpPNEYzUUpQNEI1MkZaTk9WV0lQRlRZRFA0MlZOVCIsInByZWZlcnJlZF91c2VybmFtZSI6IndldGF4MjEwNTIwMTUyMzM3MjMzNzg5MCIsIm5hbWUiOiJ3ZXRheDIxMDUyMDE1MjMzNzIzMzc4OTAiLCJwaG9uZV9udW1iZXIiOiIxMzcxNzg4ODMxNCIsInBob25lX251bWJlcl92ZXJpZmllZCI6ZmFsc2UsInVzZXJpZCI6IjQxODdkNzBkLWE5ZGEtNDZhMC05YTM0LWE5MjJiNzIzZGQ1NyIsInNlc3Npb25fa2V5IjoiTy82VVA3K3F5N0I3dmlRQWM3alNWUT09In0.j7oQjvt3MsgqrQcdAoi0uSjL3KHmtTTN5V3umky0mRu-r2iYUN3hHtrKT_k6-TpTbPmYhFInPZcfHf-0VbgEIplmhazW31ClQqI4p60jVCaKFcsBT-ZzK2msZFUmEV_9TDeRRAeHUP_52p_bUIh3oNVynRE1Qx6fiUS2Y7YGl0775dTYzFrd5LJfTSSSTUNZBB6gwg5W2bN_rbzLcu6yQa5px5WxDpM5dA4qVMwSl9AEo31RD9FsWEg7Sc4mbGL1R2DWegYJ-_mJAlSm" +
            "kbHvS_POxzovUFmW72Ym5NCWbdtwjpMMOouopznlB6K6cNqs3jHeJaAHyRiu63n9MkW5-A";


}
