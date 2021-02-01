<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:oba="http://www.obavestenje" version="2.0">
    <xsl:output encoding="UTF-8" version="1.0"/>

    <xsl:template match="/">
        <html>
            <head>
                <title>Obavestenje</title>
            </head>
            <style type="text/css">
                table {
                font-family: serif;
                border-collapse: collapse;
                margin: 50px auto 50px auto;
                width: 90%;
                }
                th, td {
                text-align: left;
                padding: 30px;
                }
                tr:nth-child(even){ background-color: #f2f2f2 }
                th {
                background-color: #4caf50;
                font-family: sans-serif;
                color: white;
                }
                tr { border: 1px solid darkgrey; }
                tr:hover {
                font-style: italic;
                background-color: #cae8cb;
                }
                body { font-family: sans-serif; }
                p { text-indent: 30px; }
                .sup {
                vertical-align: super;
                padding-left: 4px;
                font-size: small;
                text-transform: lowercase;
                }

            </style>
            <body>
                <h1>Obavestenje (XSLT)</h1>
                <div>
                    <p>obavestenje
                        <xsl:value-of select="/oba:obavestenje"/>
                    </p>
                </div>
                <h1>Obavestenje (XSLT)</h1>

                <h2>Available books:</h2>
                <p>Highlighting (*) the book titles with a <b>price less than $40</b>.</p>

            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
