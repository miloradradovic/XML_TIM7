<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:oba="http://www.obavestenje"
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

                    <fo:block font-family="Times New Roman" font-size="13px" >
                        <xsl:value-of select="/oba:obavestenje/oba:obavestenje_body/oba:naziv_organa"/>
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="13px" >
                        <xsl:value-of select="/oba:obavestenje/oba:obavestenje_body/oba:naziv_organa/@sediste"/>
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="13px" >
                        Број предмета: <xsl:value-of select="/oba:obavestenje/oba:obavestenje_body/@broj"/>
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="13px" padding-bottom="16px" >
                        Датум: <xsl:value-of select="/oba:obavestenje/oba:obavestenje_body/@content"/>
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="13px" >
                       <xsl:value-of select="/oba:obavestenje/oba:obavestenje_body/oba:informacije_o_podnosiocu/re:lice"/>
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="13px" >
                        <xsl:value-of select="/oba:obavestenje/oba:obavestenje_body/oba:informacije_o_podnosiocu/re:adresa"/>
                        <xsl:value-of select="/oba:obavestenje/oba:obavestenje_body/oba:informacije_o_podnosiocu/re:adresa/re:ulica/@broj"/>
                    </fo:block>
                    <fo:block font-family="Times New Roman" text-align="center" font-size="20px" font-weight="bold" padding-top="15px">
                        <xsl:value-of select="/oba:obavestenje/oba:obavestenje_body/oba:naslov"/>
                    </fo:block>

                    <fo:block font-family="Times New Roman" text-align="center" font-size="14px" font-weight="bold" padding-top="5px">
                        <xsl:value-of select="/oba:obavestenje/oba:obavestenje_body/oba:podnaslov"/>
                    </fo:block>

                    <fo:block font-family="Times New Roman" text-align="justify" font-size="13px"  padding-top="15px">
                        На основу члана 16. ст. 1 Закона о слободном приступу информацијама од јавног значаја, поступајући по вашем захтеву за слободан приступ информацијама од
                        <xsl:value-of select="/oba:obavestenje/oba:obavestenje_body/oba:tekst_zahteva/re:godina"/>
                        год., којим сте тражили увид у документ/е са информацијама о/у вези са:
                    </fo:block>

                    <fo:block font-family="Times New Roman" text-align="justify" font-size="13px"  padding-top="5px">
                        <xsl:apply-templates select="/oba:obavestenje/oba:obavestenje_body/oba:tekst_zahteva/re:opis_trazene_informacije"/>
                    </fo:block>

                    <fo:block font-family="Times New Roman" text-align="center" font-size="13px" >
                        (oпис тражене информације)
                    </fo:block>

                    <fo:block font-family="Times New Roman" text-align="justify" font-size="13px" padding-top="10px" >
                        обавештавамо вас да дана
                        <xsl:value-of select="/oba:obavestenje/oba:obavestenje_body/oba:tekst_zahteva/re:dan"/>
                        , у
                        <xsl:value-of select="/oba:obavestenje/oba:obavestenje_body/oba:tekst_zahteva/re:vreme"/>
                        часова, односно у времену од
                        <xsl:value-of select="/oba:obavestenje/oba:obavestenje_body/oba:tekst_zahteva/re:radno_vreme/re:pocetak"/>
                        до
                        <xsl:value-of select="/oba:obavestenje/oba:obavestenje_body/oba:tekst_zahteva/re:radno_vreme/re:kraj"/>
                        часова, у просторијама органа у
                        <xsl:value-of select="/oba:obavestenje/oba:obavestenje_body/oba:tekst_zahteva/re:adresa/re:mesto"/>
                        ул.
                        <xsl:value-of select="/oba:obavestenje/oba:obavestenje_body/oba:tekst_zahteva/re:adresa/re:ulica"/>
                        бр.
                        <xsl:value-of select="/oba:obavestenje/oba:obavestenje_body/oba:tekst_zahteva/re:adresa/re:ulica/@broj"/>
                        , канцеларија бр.
                        <xsl:value-of select="/oba:obavestenje/oba:obavestenje_body/oba:tekst_zahteva/re:broj_kancelarije"/>
                        можете извршити увид у документ/е у коме је садржана тражена информација.
                    </fo:block>
                    <fo:block font-family="Times New Roman" text-align="justify"  font-size="13px" padding-top="10px">
                        Том приликом, на ваш захтев, може вам се издати и копија документа са траженом информацијом.
                    </fo:block>
                    <fo:block font-family="Times New Roman" text-align="justify"  font-size="13px" padding-top="10px">
                        Трошкови су утврђени Уредбом Владе Републике Србије („Сл. гласник РС“, бр. 8/06), и то: копија стране А4
                        формата износи 3 динара, А3 формата 6 динара, CD 35 динара, дискете 20 динара, DVD 40 динара, аудио-касета
                        – 150 динара, видео-касета 300 динара, претварање једне стране документа из физичког у електронски облик – 30 динара.
                    </fo:block>
                    <fo:block font-family="Times New Roman" text-align="justify"  font-size="13px" padding-top="10px">
                        Износ укупних трошкова израде копије документа по вашем захтеву износи
                        <xsl:value-of select="/oba:obavestenje/oba:obavestenje_body/oba:tekst_zahteva/re:ukupan_trosak/re:iznos"/>
                        динара и уплаћује се на жиро-рачун Буџета Републике Србије бр. 840-742328-843-30, с позивом на број 97 – ознака
                        шифре општине/града где се налази орган власти (из Правилника о условима и начину вођења рачуна – „Сл. гласник РС“, 20/07... 40/10).
                    </fo:block>

                    <fo:block padding-top="10px">
                        <fo:inline-container inline-progression-dimension="24%">
                            <fo:block font-family="Times New Roman" text-align="justify" font-size="13px" padding-top="10px">
                                Достављено:
                                <xsl:for-each select="/oba:obavestenje/oba:obavestenje_body/oba:opcija_dostave/re:opcija">
                                    <xsl:if test="@izabran='true'">
                                        <fo:block>
                                            <xsl:value-of select="position()"/>.
                                            <xsl:value-of select="."/>
                                        </fo:block>
                                    </xsl:if>
                                </xsl:for-each>
                            </fo:block>
                        </fo:inline-container>
                        <fo:inline-container font-family="Times New Roman" text-align="center" inline-progression-dimension="35%">
                            <fo:block>
                                (М.П)
                            </fo:block>

                        </fo:inline-container>
                        <fo:inline-container font-family="Times New Roman" text-align="center" inline-progression-dimension="40%">
                            <fo:block>
                                _________________________
                            </fo:block>
                            <fo:block>
                                (Потпис овлашћеног лица, односно руководиоца органа)
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>
                    <fo:block>
                        <fo:basic-link external-destination="url({/oba:obavestenje/oba:obavestenje_body/@link_na_zahtev})"  font-family="Times New Roman" text-altitude="2cm" color="blue">
                                Референцирани захтев
                        </fo:basic-link>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
