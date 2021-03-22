export interface MenuItem {
  id?: string;
  href?: string;
  objectTypeId?: string;
  parent?: string;
  name?: string;
  icon?: string;
  items?: MenuItem[];
  dummy?: boolean;
}
