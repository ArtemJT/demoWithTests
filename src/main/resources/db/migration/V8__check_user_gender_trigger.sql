CREATE OR REPLACE FUNCTION check_employees_gender()
    RETURNS TRIGGER
AS $$
BEGIN
    IF NEW.gender IS NULL THEN
        RAISE EXCEPTION 'Gender cannot be null';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_user_gender_check
    BEFORE INSERT OR UPDATE ON users
    FOR EACH ROW
EXECUTE FUNCTION check_employees_gender();