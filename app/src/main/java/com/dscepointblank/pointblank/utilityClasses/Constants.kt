package com.dscepointblank.pointblank.utilityClasses

import com.dscepointblank.pointblank.R
import java.text.SimpleDateFormat
import java.util.*

class Constants {
    companion object {
        const val BASE_URL = "https://fcm.googleapis.com"
        const val SERVER_KEY =
            "AAAA0JlC_34:APA91bGQFpK_Zm7UwhAB2UrJ-hRP-VOgUL_sL0K-0Yw99E2fViQe0LByY3RSiNUsv_mivCHcKJZfmVRCSAd85roQgr3LgGTmYnsyr2JQDWQ9dpg7v_pQrxj1KfUEu9w1E0Cdaaftx184"
        const val CONTENT_TYPE = "application/json"

        const val CODE_FORCES_BASE_URL = "https://codeforces.com"

        const val LINK = "link"

        const val IS_VIEW = "view"
        const val LINK_VIEW = "linkView"
        const val LINK_FORUM_DSCE = 1
        const val LINK_WRITEUP_DSCE =2

//        "Bad or missing mouse driver. Spank the cat [Y/N]?",

        //Update Constants
        const val UPDATE_COLLECTION = "UPDATE"
        const val UPDATE_DOCUMENT = "NEWVERSION"
        const val CURRENT_APK_VERSION = 4

        val tagLines = arrayOf(
            "Its not a bug, its an undocumented feature",
            "BB is not a feeling, its an Emotion",
            "To understand what recursion is, you must first understand recursion",
            "We know the right keystrokes to turn things on. ;)",
            "Once you start programming, you no longer have a life",
            "The name is Baud......, James Baud.",
            "Do not be afraid to step on people.\nMario made a career from it",
            "Become a programmer, lose your brain’s virginity",
            "Take a <\\br>",
            "Not sure, if you are a good programmer or good at googling",
            "Valar Codilis. All men must code.",
            "F"
        )
        val tagLineSize = tagLines.size

        const val TOPIC = "/topics/MyTopic"


        const val CLIST_API_KEY = "96dcff73025ae5a23f4a5e42e7559c128a3bd09a"
        const val CLIST_BASE_URL = "https://clist.by"


        private const val CODE_CHEF_RESOURCE_ID: Int = 2
        private const val HACKER_RANK_RESOURCE_ID: Int = 63
        private const val LEET_CODE_RESOURCE_ID: Int = 102
        private const val HACKER_EARTH_RESOURCE_ID: Int = 102
        private const val FACE_BOOK: Int = 29
        private const val CODING_IN_GAME_RESOURCE_ID = 81
        private const val CODE_FORCES_RESOURCE_ID = 1

        val CONTEST_ARRAY = arrayOf(
            HACKER_RANK_RESOURCE_ID, CODE_CHEF_RESOURCE_ID,
            LEET_CODE_RESOURCE_ID, HACKER_EARTH_RESOURCE_ID, FACE_BOOK, CODING_IN_GAME_RESOURCE_ID,
            CODE_FORCES_RESOURCE_ID
        )

        val CONTEST_WEBSITE_NAME = hashMapOf<Int, String>(
            CODE_CHEF_RESOURCE_ID to "Code Chef",
            HACKER_RANK_RESOURCE_ID to "Hacker Rank",
            LEET_CODE_RESOURCE_ID to "Leet Code",
            HACKER_EARTH_RESOURCE_ID to "Hacker earth",
            FACE_BOOK to "Face Book",
            CODING_IN_GAME_RESOURCE_ID to "Coding Game",
            CODE_FORCES_RESOURCE_ID to "Code Forces"
        )

        val CONTEST_WEBSITE_COLOR = hashMapOf<Int, Int>(
            CODE_CHEF_RESOURCE_ID to R.color.codeChef,
            HACKER_RANK_RESOURCE_ID to R.color.hackerRank,
            LEET_CODE_RESOURCE_ID to R.color.leetCode,
            HACKER_EARTH_RESOURCE_ID to R.color.hackerEarth,
            FACE_BOOK to R.color.faceBook,
            CODING_IN_GAME_RESOURCE_ID to R.color.codingInGame,
            CODE_FORCES_RESOURCE_ID to R.color.codeForces
        )

        val fromSDF = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss", Locale.US)
        val toSDF = SimpleDateFormat("dd MMM '@' hh:mm aa", Locale.US)

        fun getDate(): String {
            val fromSDF = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss", Locale.US)
            val cal = Calendar.getInstance()
            cal.set(Calendar.DAY_OF_MONTH, 1)
            cal.set(Calendar.SECOND, 0)
            cal.set(Calendar.MILLISECOND, 0)
            cal.set(Calendar.HOUR, 0)

            return fromSDF.format(cal.timeInMillis)
        }
    }
}
