-- ------------------------------------------------
-- Version: v1.6
--
-- Description: Update Prefix
-- -------------------------------------------------
ALTER TABLE prefix
ADD COLUMN resolvable BOOLEAN DEFAULT TRUE;