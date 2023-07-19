DO $$
BEGIN
   IF NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'rocket') THEN
      CREATE DATABASE rocket;
END IF;
END 
$$;