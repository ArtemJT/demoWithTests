CREATE OR REPLACE FUNCTION check_passport_series()
    RETURNS TRIGGER
AS $$
BEGIN
    IF NEW.series NOT SIMILAR TO '[A-Z][A-Z]' THEN
        RAISE EXCEPTION 'Series must contains of TWO (A-Z) symbols';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_passport_series_check
    BEFORE INSERT OR UPDATE ON passport
    FOR EACH ROW
EXECUTE FUNCTION check_passport_series();