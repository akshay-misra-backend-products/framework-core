export interface MenuItem {
  id?: string;
  objectTypeId?: string;
  parent?: string;
  name?: string;
  icon?: string;
  items?: MenuItem[];
  dummy?: boolean;
}
