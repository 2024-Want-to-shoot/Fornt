package com.example.a2024_want_to_shoot

sealed class ActionItem {

    data class Header(val title: String) : ActionItem()
    data class Action(
        val name: String,
        val category: String,
        val detailsName: String,
        val activityDetails: String,
        var isBookmark: Boolean
    ) : ActionItem()

}

// 더미 데이터 생성 함수
fun getDummyData(): List<ActionItem> {
    return listOf(
        ActionItem.Header("지수님은 내일의 득점왕!! 슛 배우기"),
        ActionItem.Action("레이업 슛", "슛", "레이업 슛(lay-up shoot)", "농구에서, 골 가까이에서 뛰어올라 손바닥에 공을\n 올려 가볍게 던져 넣는 슛.", false),
        ActionItem.Action("원 핸드 슛", "슛", "원 핸드 슛(One-Handed Shot)", "한 손으로 공을 던져 림을 겨냥하는 슛.\n 정통적인 슈팅 폼에서 자주 사용됨.",true),
        ActionItem.Action("스탠딩 슛", "슛","스탠딩 슛(Standing Shot)", "점프 없이 고정된 자세에서 림을 향해 던지는 슛.\n 자유투 시 많이 사용됨.", false),
        ActionItem.Action("점프 슛", "슛", "점프 슛(Jump Shot)", "점프하며 공을 던지는 슛.\n 상대 수비를 피하거나 거리에서 림을 겨냥할 때 사용.", false),
        ActionItem.Action("덩크 슛", "슛", "덩크 슛(Dunk Shot)", "점프해서 공을 림에 직접 넣는 강력한 슛.", true),
        ActionItem.Header("농구의 기본! 드리블 배우기"),
        ActionItem.Action("리버스 드리블", "드리블", "리버스 드리블(Reverse Dribble)", "몸을 반대 방향으로 돌리면서\n수비를 피해 진행하는 드리블.",  false),
        ActionItem.Action("체인지 드리블", "드리블", "체인지 드리블(Crossover Dribble)", "공을 한 손에서 다른 손으로 바꿔 상대를 속이는 드리블.",  false),
        ActionItem.Action("스핀무브", "드리블", "스핀무브(Spin Move)", "몸을 회전하며 수비수를 따돌리는 드리블 기술.", true),
        ActionItem.Action("레그 스루 드리블", "드리블", "레그 스루 드리블(Between-the-Legs Dribble)", "공을 다리 사이로 드리블해 상대를 속이는 기술.", false),
        ActionItem.Action("비하인드 백 드리블", "드리블", "비하인드 백 드리블(Behind-the-Back Dribble)", "공을 뒤로 보내 다른 손으로 받는 드리블.\n수비를 속일 때 효과적.", true),
        ActionItem.Header("느슨한 경기장에 긴장감을 줘! 스탭 배우기"),
        ActionItem.Action("필드 스탭", "스탭", "필드 스탭(Field Step)", "공간을 활용하며 발을 넓게 움직여\n공수 전환에 도움을 주는 기본 스탭.", false),
        ActionItem.Action("점프 스탭", "스탭", "점프 스탭(Jump Stop)", "점프 후 두 발로 동시에 착지하여\n균형을 잡는 기술.", true),
        ActionItem.Action("스위치 스탭", "스탭", "스위치 스탭(Switch Step)", "방향을 전환하며\n수비나 드리블 진행을 도와주는 스탭.", false),
        ActionItem.Action("인 스탭", "스탭", "인 스탭(In Step)", "몸을 상대 방향으로 깊숙이 밀어넣으며\n공간을 확보하는 기술.", false),
        ActionItem.Header("농구는 6명이 한 팀이야! 패스 배우기"),
        ActionItem.Action("노룩 패스", "패스", "노룩 패스(No-Look Pass", "시선을 다른 방향으로 향하며 팀 동료에게\n공을 전달하는 패스. 상대를 혼란스럽게 함.", false),
        ActionItem.Header("우리 슈터에게 자유를! 스크린 배우기"),
        ActionItem.Action("픽 앤 팝", "스크린", "픽 앤 팝 (Pick and Pop)", "스크린 후 골대로 롤 인하지 않고 외곽에서\n슛 찬스를 만드는 플레이.", false),
        ActionItem.Action("픽 롤 앤", "스크린", "픽 롤 앤 (Pick and Roll)", "스크린 후 골대로 롤 인해 득점 기회를 만드는 플레이.", false),
        ActionItem.Action("백 스크린", "스크린", "백 스크린 (Back Screen)", "공을 갖고 있지 않은 선수가 뒤에서 수비를 막아\n팀 동료를 열어주는 스크린.", false),
        ActionItem.Action("프론트 스크린", "스크린", "프론트 스크린 (Front Screen)", "공이 있는 방향에서 수비를 막아\n동료에게 공간을 만드는 스크린.", false)
    )
}

