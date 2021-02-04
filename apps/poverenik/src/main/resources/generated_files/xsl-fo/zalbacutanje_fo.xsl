<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:zc="http://www.zalbacutanje"
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
                        ЖАЛБА КАДА ОРГАН ВЛАСТИ НИЈЕ ПОСТУПИО/ није поступио у целости/ ПО ЗАХТЕВУ ТРАЖИОЦА У ЗАКОНСКОМ  РОКУ  (ЋУТАЊЕ УПРАВЕ)
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="12px" padding-top="10px" font-weight="bold">
                        Повереникy за информације од јавног значаја и заштиту података о личности
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="12px" >
                        Адреса за пошту:  Београд, Булевар краља Александрa бр. 15
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="12px" padding-top="15px" >
                        У складу са чланом 22. Закона о слободном приступу информацијама од јавног значаја подносим:
                    </fo:block>
                    <fo:block font-family="Times New Roman" text-align="center" font-size="18px" font-weight="bold" padding-top="5px">
                        Ж А Л Б У
                    </fo:block>
                    <fo:block font-family="Times New Roman" text-align="center" font-size="12px" padding-top="5px">
                        против
                    </fo:block>
                    <fo:block font-family="Times New Roman" text-align="center" font-size="12px" padding-top="15px">
                        <xsl:value-of select="/zc:zalba_cutanje/zc:zalba_cutanje_body/zc:sadrzaj_zalbe/re:ciljani_organ_vlasti"/>
                    </fo:block>
                    <fo:block font-family="Times New Roman" text-align="center" font-size="12px" padding-top="15px">
                        због тога што орган власти:
                    </fo:block>
                    <fo:block font-family="Times New Roman" text-align="center" font-weight="bold" font-size="12px" padding-top="5px">
                        <xsl:for-each select="/zc:zalba_cutanje/zc:zalba_cutanje_body/zc:sadrzaj_zalbe/re:razlog_zalbe/re:opcija">
                            <xsl:if test="@izabran='true'">
                                <xsl:value-of select="."/>
                            </xsl:if>
                        </xsl:for-each>
                    </fo:block>


                    <fo:block font-family="Times New Roman" text-align="justify" font-size="12px" padding-top="15px">
                        по мом захтеву  за слободан приступ информацијама од јавног значаја који сам поднео  том органу  дана
                        <xsl:value-of select="/zc:zalba_cutanje/zc:zalba_cutanje_body/zc:sadrzaj_zalbe/re:datum"/>
                        године,
                        а којим сам тражио/ла да ми се у складу са Законом о слободном приступу информацијама од јавног значаја омогући увид-
                        копија документа који садржи информације  о /у вези са :
                    </fo:block>
                    <fo:block font-family="Times New Roman" text-align="center" font-size="12px" padding-top="5px">
                        <xsl:value-of select="/zc:zalba_cutanje/zc:zalba_cutanje_body/zc:sadrzaj_zalbe/re:podaci_o_zahtjevu_i_informacijama"/>
                    </fo:block>
                    <fo:block font-family="Times New Roman" text-align="justify" font-size="12px" padding-top="15px">
                        На основу изнетог, предлажем да Повереник уважи моју жалбу и омогући ми приступ траженој/им  информацији/ма.
                    </fo:block>
                    <fo:block font-family="Times New Roman" text-align="justify" font-size="12px" padding-top="5px">
                        Као доказ , уз жалбу достављам копију захтева са доказом о предаји органу власти.
                    </fo:block>
                    <fo:block font-family="Times New Roman" text-align="justify" font-size="12px" padding-top="5px">
                        <fo:inline-container inline-progression-dimension="15%">
                            <fo:block font-weight="bold">
                                Напомена:
                            </fo:block>
                        </fo:inline-container>
                        <fo:inline-container>
                            <fo:block>
                                Код жалбе  због непоступању по захтеву у целости, треба приложити и добијени
                            </fo:block>
                        </fo:inline-container>
                        <fo:block>
                                одговор органа власти.
                        </fo:block>
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12px" padding-top="10px" text-align="right">
                        <fo:block padding-top="5px">
                            <xsl:value-of select="/zc:zalba_cutanje/zc:zalba_cutanje_body/zc:podaci_o_podnosiocu/re:osoba"/>
                        </fo:block>
                        <fo:block padding-top="5px">
                            <xsl:value-of select="/zc:zalba_cutanje/zc:zalba_cutanje_body/zc:podaci_o_podnosiocu/re:adresa"/>
                            <xsl:value-of select="/zc:zalba_cutanje/zc:zalba_cutanje_body/zc:podaci_o_podnosiocu/re:adresa/re:ulica/@broj"/>
                        </fo:block>
                        <fo:block padding-top="5px">
                            <xsl:value-of select="/zc:zalba_cutanje/zc:zalba_cutanje_body/zc:podaci_o_podnosiocu/re:drugi_podaci_za_kontakt"/>
                        </fo:block>
                        <fo:block padding-top="5px">
                            _________________
                        </fo:block>
                        <fo:block>
                            Потпис
                        </fo:block>
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="12px">
                        У <xsl:value-of select="/zc:zalba_cutanje/zc:zalba_cutanje_body/zc:podaci_o_primaocu/re:adresa/re:mesto"/>,
                        дана <xsl:value-of select="/zc:zalba_cutanje/zc:zalba_cutanje_body/@content"/>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
