package com.wombat.mediastore_test

object Constant {

    // 7
    object Image {
        val jpg = "https://file-examples-com.github.io/uploads/2017/10/file_example_JPG_100kB.jpg"
        val png = "https://file-examples-com.github.io/uploads/2017/10/file_example_PNG_500kB.png"
        val gif = "https://file-examples-com.github.io/uploads/2017/10/file_example_GIF_500kB.gif"
        val tiff =
            "https://file-examples-com.github.io/uploads/2017/10/file_example_TIFF_1MB.tiff"
        val ico = "https://file-examples-com.github.io/uploads/2017/10/file_example_favicon.ico"
        val svg = "https://file-examples-com.github.io/uploads/2020/03/file_example_SVG_30kB.svg"
        val webp =
            "https://file-examples-com.github.io/uploads/2020/03/file_example_WEBP_250kB.webp"
    }

    // 6
    object Video {
        val avi =
            "https://file-examples-com.github.io/uploads/2018/04/file_example_AVI_480_750kB.avi"
        val mov =
            "https://file-examples-com.github.io/uploads/2018/04/file_example_MOV_480_700kB.mov"
        val mp4 =
            "https://file-examples-com.github.io/uploads/2017/04/file_example_MP4_480_1_5MG.mp4"
        val ogg =
            "https://file-examples-com.github.io/uploads/2018/04/file_example_OGG_480_1_7mg.ogg"
        val wmv =
            "https://file-examples-com.github.io/uploads/2018/04/file_example_WMV_480_1_2MB.wmv"
        val webm =
            "https://file-examples-com.github.io/uploads/2020/03/file_example_WEBM_480_900KB.webm"
    }

    // 3
    object Audio {
        val mp3 = "https://file-examples-com.github.io/uploads/2017/11/file_example_MP3_700KB.mp3"
        val wav = "https://file-examples-com.github.io/uploads/2017/11/file_example_WAV_1MG.wav"
        val ogg = "https://file-examples-com.github.io/uploads/2017/11/file_example_OOG_1MG.ogg"
    }

    // 10
    object Document {
        val doc = "https://file-examples-com.github.io/uploads/2017/02/file-sample_500kB.doc"
        val docx = "https://file-examples-com.github.io/uploads/2017/02/file-sample_500kB.docx"
        val xls = "https://file-examples-com.github.io/uploads/2017/02/file_example_XLS_5000.xls"
        val xlsx = "https://file-examples-com.github.io/uploads/2017/02/file_example_XLSX_5000.xlsx"
        val pptx = "https://file-examples-com.github.io/uploads/2017/08/file_example_PPT_500kB.ppt"
        val pdf = "https://file-examples-com.github.io/uploads/2017/10/file-example_PDF_500_kB.pdf"
        val odt = "https://file-examples-com.github.io/uploads/2017/10/file-sample_500kB.odt"
        val ods = "https://file-examples-com.github.io/uploads/2017/10/file_example_ODS_5000.ods"
        val odp = "https://file-examples-com.github.io/uploads/2017/10/file_example_ODP_500kB.odp"
        val rtf = "https://file-examples-com.github.io/uploads/2019/09/file-sample_500kB.rtf"
    }

    // 5
    object Other {
        val csv = "https://file-examples-com.github.io/uploads/2017/02/file_example_CSV_5000.csv"
        val json = "https://file-examples-com.github.io/uploads/2017/02/file_example_JSON_1kb.json"
        val xml = "https://file-examples-com.github.io/uploads/2017/02/file_example_XML_24kb.xml"
        val html = "https://file-examples-com.github.io/uploads/2017/02/index.html"
        val zip = "https://file-examples-com.github.io/uploads/2017/02/zip_2MB.zip"

    }

}

enum class FileUrl(val url: String) {
    jpg(Constant.Image.jpg),
    png(Constant.Image.png),
    gif(Constant.Image.gif),
    tiff(Constant.Image.tiff),
    ico(Constant.Image.ico),
    svg(Constant.Image.svg),
    webp(Constant.Image.webp),

    avi(Constant.Video.avi),
    mov(Constant.Video.mov),
    mp4(Constant.Video.mp4),
    ogg(Constant.Video.ogg),
    wmv(Constant.Video.wmv),
    webm(Constant.Video.webm),

    mp3(Constant.Audio.mp3),
    wav(Constant.Audio.wav),
//    ogg(Constant.Audio.ogg),

    doc(Constant.Document.doc),
    docx(Constant.Document.docx),
    xls(Constant.Document.xls),
    xlsx(Constant.Document.xlsx),
    pptx(Constant.Document.pptx),

    pdf(Constant.Document.pdf),
    odt(Constant.Document.odt),
    ods(Constant.Document.ods),
    odp(Constant.Document.odp),
    rtf(Constant.Document.rtf),

    csv(Constant.Other.csv),
    json(Constant.Other.json),
    xml(Constant.Other.xml),
    html(Constant.Other.html),
    zip(Constant.Other.zip)
}