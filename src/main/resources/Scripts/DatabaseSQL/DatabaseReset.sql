DO
$$
    DECLARE
        r RECORD;
    BEGIN
        SET session_replication_role = 'replica';

        FOR r IN (SELECT tablename FROM pg_tables WHERE schemaname = 'public')
            LOOP
                EXECUTE 'DROP TABLE IF EXISTS public.' || quote_ident(r.tablename) || ' CASCADE';
            END LOOP;

        FOR r IN (SELECT sequence_name FROM information_schema.sequences WHERE sequence_schema = 'public')
            LOOP
                EXECUTE 'DROP SEQUENCE IF EXISTS public.' || quote_ident(r.sequence_name) || ' CASCADE';
            END LOOP;

        FOR r IN (SELECT table_name FROM information_schema.views WHERE table_schema = 'public')
            LOOP
                EXECUTE 'DROP VIEW IF EXISTS public.' || quote_ident(r.table_name) || ' CASCADE';
            END LOOP;

        FOR r IN (SELECT routine_name FROM information_schema.routines WHERE routine_schema = 'public')
            LOOP
                EXECUTE 'DROP FUNCTION IF EXISTS public.' || quote_ident(r.routine_name) || ' CASCADE';
            END LOOP;

        FOR r IN (SELECT typname
                  FROM pg_type
                  WHERE typnamespace = (SELECT oid FROM pg_namespace WHERE nspname = 'public'))
            LOOP
                EXECUTE 'DROP TYPE IF EXISTS public.' || quote_ident(r.typname) || ' CASCADE';
            END LOOP;

        FOR r IN (SELECT grantee, table_name
                  FROM information_schema.role_table_grants
                  WHERE table_schema = 'public')
            LOOP
                EXECUTE 'REVOKE ALL ON public.' || quote_ident(r.table_name) || ' FROM ' || r.grantee;
            END LOOP;

        FOR r IN (SELECT grantee, routine_name
                  FROM information_schema.role_routine_grants
                  WHERE specific_schema = 'public')
            LOOP
                EXECUTE 'REVOKE ALL ON FUNCTION public.' || quote_ident(r.routine_name) || ' FROM ' || r.grantee;
            END LOOP;

        SET session_replication_role = 'origin';
    END
$$;
