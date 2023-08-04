CREATE OR REPLACE FUNCTION check_users_email()
    RETURNS TRIGGER
AS $$
BEGIN
    IF NEW.email NOT SIMILAR TO '%@%.%' THEN
        RAISE EXCEPTION 'Email must contains @ symbol';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_user_email_check
    BEFORE INSERT OR UPDATE ON users
    FOR EACH ROW
EXECUTE FUNCTION check_users_email();