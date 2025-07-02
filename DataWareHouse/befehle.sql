-- 1. Weihnachtsgeldzahlungen
-- Nur Mitarbeiter, die Weihnachtsgeld bekommen haben
SELECT * FROM gehaltszahlung WHERE bemerkung LIKE 'Weihnachtsgeld%' GROUP BY pnr;
-- Nur Mitarbeiter, die kein Weihnachtsgeld bekommen haben
SELECT * FROM gehaltszahlung WHERE bemerkung NOT LIKE 'Weihnachtsgeld%' AND pnr NOT IN (
    SELECT pnr FROM gehaltszahlung WHERE bemerkung LIKE 'Weihnachtsgeld%' GROUP BY pnr
) GROUP BY pnr;


-- 2. Urlaubsgeldzahlungen
-- Nur Monate, in denen Urlaubsgeld gezahlt wurde
SELECT DISTINCT MONTHNAME(zahlungsdatum) AS Urlaubsgeldauszahlungsmonat FROM gehaltszahlung WHERE bemerkung LIKE 'Urlaubsgeld%' ORDER BY Urlaubsgeldauszahlungsmonat;

