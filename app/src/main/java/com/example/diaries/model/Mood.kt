package com.example.diaries.model

import androidx.compose.ui.graphics.Color
import com.example.diaries.R
import com.example.diaries.ui.theme.AngryColor
import com.example.diaries.ui.theme.AwfulColor
import com.example.diaries.ui.theme.BoredColor
import com.example.diaries.ui.theme.CalmColor
import com.example.diaries.ui.theme.DepressedColor
import com.example.diaries.ui.theme.DisappointedColor
import com.example.diaries.ui.theme.HappyColor
import com.example.diaries.ui.theme.HumorousColor
import com.example.diaries.ui.theme.LonelyColor
import com.example.diaries.ui.theme.MysteriousColor
import com.example.diaries.ui.theme.NeutralColor
import com.example.diaries.ui.theme.RomanticColor
import com.example.diaries.ui.theme.ShamefulColor
import com.example.diaries.ui.theme.SurprisedColor
import com.example.diaries.ui.theme.SuspiciousColor
import com.example.diaries.ui.theme.TenseColor

enum class Mood (
    val icon: Int,
    val textColor: Color,
    val moodColor: Color
) {
    Neutral(
        icon = R.drawable.neutral,
        textColor = Color.Black,
        moodColor = NeutralColor
    ),
    Happy(
        icon = R.drawable.happy,
        textColor = Color.Black,
        moodColor = HappyColor
    ),
    Angry(
        icon = R.drawable.angry,
        textColor = Color.White,
        moodColor = AngryColor
    ),
    Bored(
        icon = R.drawable.bored,
        textColor = Color.Black,
        moodColor = BoredColor
    ),
    Calm(
        icon = R.drawable.calm,
        textColor = Color.Black,
        moodColor = CalmColor
    ),
    Depressed(
        icon = R.drawable.depressed,
        textColor = Color.Black,
        moodColor = DepressedColor
    ),
    Disappointed(
        icon = R.drawable.disappointed,
        textColor = Color.White,
        moodColor = DisappointedColor
    ),
    Humorous(
        icon = R.drawable.humorous,
        textColor = Color.Black,
        moodColor = HumorousColor
    ),
    Lonely(
        icon = R.drawable.lonely,
        textColor = Color.White,
        moodColor = LonelyColor
    ),
    Mysterious(
        icon = R.drawable.mysterious,
        textColor = Color.Black,
        moodColor = MysteriousColor
    ),
    Romantic(
        icon = R.drawable.romantic,
        textColor = Color.White,
        moodColor = RomanticColor
    ),
    Shameful(
        icon = R.drawable.shameful,
        textColor = Color.White,
        moodColor = ShamefulColor
    ),
    Awful(
        icon = R.drawable.awful,
        textColor = Color.Black,
        moodColor = AwfulColor
    ),
    Surprised(
        icon = R.drawable.surprised,
        textColor = Color.Black,
        moodColor = SurprisedColor
    ),
    Suspicious(
        icon = R.drawable.suspicious,
        textColor = Color.Black,
        moodColor = SuspiciousColor
    ),
    Tense(
        icon = R.drawable.tense,
        textColor = Color.Black,
        moodColor = TenseColor
    )

}