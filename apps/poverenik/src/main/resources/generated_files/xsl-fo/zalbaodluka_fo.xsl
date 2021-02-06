<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:zoc="http://www.zalbanaodlukucir"
                xmlns:re="http://www.reusability"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">

    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="A4">
                    <fo:region-body margin="1in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="A4">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-family="Times New Roman" font-size="13px" text-align="center" font-weight="bold"  >
                        ЖАЛБА  ПРОТИВ  ОДЛУКЕ ОРГАНА  ВЛАСТИ КОЈОМ ЈЕ
                        ОДБИЈЕН ИЛИ ОДБАЧЕН ЗАХТЕВ ЗА ПРИСТУП ИНФОРМАЦИЈИ
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="12px" padding-top="10px" font-weight="bold">
                        Повереникy за информације од јавног значаја и заштиту података о личности
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="12px" >
                        Адреса за пошту:  Београд, Булевар краља Александрa бр. 15
                    </fo:block>
                    <fo:block font-family="Times New Roman" text-align="center" font-size="18px" font-weight="bold" padding-top="15px">
                        Ж А Л Б А
                    </fo:block>
                    <fo:block font-family="Times New Roman" text-align="center" font-weight="bold" font-size="12px" padding-top="15px">

                        <xsl:value-of select="/zoc:zalba_odluka/zoc:zalba_odluka_body/zoc:zalilac/re:tip_lica/re:osoba"/>
                        <xsl:value-of select="/zoc:zalba_odluka/zoc:zalba_odluka_body/zoc:zalilac/re:sediste_zalioca"/>
                        <xsl:value-of select="/zoc:zalba_odluka/zoc:zalba_odluka_body/zoc:zalilac/re:adresa/re:ulica"/>
                        <xsl:value-of select="/zoc:zalba_odluka/zoc:zalba_odluka_body/zoc:zalilac/re:adresa/re:ulica/@broj"/>
                    </fo:block>
                    <fo:block font-family="Times New Roman" text-align="center" font-size="12px">
                        (Име, презиме, односно назив,седиште жалиоца и адреса)
                    </fo:block>
                    <fo:block font-family="Times New Roman" text-align="center" font-size="12px" padding-top="15px">
                        против решења-закључка
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-weight="bold" text-align="center" font-size="12px" padding-top="15px">
                        <xsl:value-of select="/zoc:zalba_odluka/zoc:zalba_odluka_body/zoc:protiv_resenja_zakljucka/re:naziv_organa_koji_je_doneo_odluku"/>
                    </fo:block>
                    <fo:block font-family="Times New Roman" text-align="center" font-size="12px" >
                        (назив органа који је донео одлуку)
                    </fo:block>
                    <fo:block  font-family="Times New Roman" text-align="justify" font-size="12px" padding-top="15px">
                        Број
                        <xsl:value-of select="/zoc:zalba_odluka/zoc:zalba_odluka_body/zoc:protiv_resenja_zakljucka/re:broj"/>
                        од
                        <xsl:value-of select="/zoc:zalba_odluka/zoc:zalba_odluka_body/zoc:protiv_resenja_zakljucka/re:od_godine"/>
                        године.
                    </fo:block>
                    <fo:block font-family="Times New Roman" text-align="justify" font-size="12px" padding-top="15px">
                        Наведеном одлуком органа власти (решењем, закључком, обавештењем у писаној форми са елементима
                        одлуке) , супротно закону, одбијен-одбачен је мој захтев који сам поднео/ла-упутио/ла дана
                        <xsl:value-of select="/zoc:zalba_odluka/zoc:zalba_odluka_body/zoc:sadrzaj/re:datum"/>
                        године и тако ми ускраћено-онемогућено остваривање уставног и законског права на слободан
                        приступ информацијама од јавног значаја. Oдлуку побијам у целости, односно у делу којим.
                        <xsl:value-of select="/zoc:zalba_odluka/zoc:zalba_odluka_body/zoc:sadrzaj/re:osnova_za_zalbu"/>
                        јер није заснована на Закону о слободном приступу информацијама од јавног значаја.
                    </fo:block>
                    <fo:block font-family="Times New Roman" text-align="justify" font-size="12px" padding-top="5px">
                        На основу изнетих разлога, предлажем да Повереник уважи моју жалбу,  поништи одлука првостепеног
                        органа и омогући ми приступ траженој/им  информацији/ма.
                    </fo:block>
                    <fo:block font-family="Times New Roman" text-align="justify" font-size="12px" padding-top="5px">
                        Жалбу подносим благовремено, у законском року утврђеном у члану 22. ст. 1.
                        Закона о слободном приступу информацијама од јавног значаја.
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12px" padding-top="10px" text-align="right">
                        <fo:block padding-top="5px">
                            <xsl:value-of select="/zoc:zalba_odluka/zoc:zalba_odluka_body/zoc:podaci_o_podnosiocu_zalbe/re:osoba"/>
                        </fo:block>
                        <fo:block padding-top="5px">
                            <xsl:value-of select="/zoc:zalba_odluka/zoc:zalba_odluka_body/zoc:podaci_o_podnosiocu_zalbe/re:adresa"/>
                            <xsl:value-of select="/zoc:zalba_odluka/zoc:zalba_odluka_body/zoc:podaci_o_podnosiocu_zalbe/re:adresa/re:ulica/@broj"/>
                        </fo:block>
                        <fo:block padding-top="5px">
                            <xsl:value-of select="/zoc:zalba_odluka/zoc:zalba_odluka_body/zoc:podaci_o_podnosiocu_zalbe/re:drugi_podaci_za_kontakt"/>
                        </fo:block>
                        <fo:block padding-top="5px">
                            _________________
                        </fo:block>
                        <fo:block>
                            Потпис
                        </fo:block>
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12px" >
                        У <xsl:value-of select="/zoc:zalba_odluka/zoc:zalba_odluka_body/zoc:zalilac/re:sediste_zalioca"/>,
                        дана <xsl:value-of select="/zoc:zalba_odluka/zoc:zalba_odluka_body/@content"/>
                    </fo:block>-->

                    <fo:block font-family="Times New Roman" font-weight="bold" font-size="12px" padding-top="10px">
                        Напомена:
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11px">
                        •	У жалби се мора навести одлука која се побија (решење, закључак, обавештење),
                        назив органа који је одлуку донео, као и број и датум одлуке. Довољно је да жалилац
                        наведе у жалби у ком погледу је незадовољан одлуком, с тим да жалбу не мора посебно образложити.
                        Ако жалбу изјављује на овом обрасцу, додатно образложење може  посебно приложити.
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11px">
                        •	Уз жалбу обавезно приложити копију поднетог захтева и доказ о његовој предаји-упућивању
                        органу као и копију одлуке органа која се оспорава жалбом.
                    </fo:block>
                    <fo:block>
                        <fo:basic-link external-destination="url({/zoc:zalba_cutanje/zoc:zalba_cutanje_body/zoc:zahtev/@href})"  font-family="Times New Roman" text-altitude="2cm" color="blue">
                            Референцирани захтев
                        </fo:basic-link>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
