import {Column} from './column';

export interface DynamicTableConfig {
  name?: string;
  objectTypeId?: string;
  parentId?: string;
  loadAPI?: string;
  createAPI?: string;
  deleteAPI?: string;
  detailsAPI?: string;
  createByObjectType?: boolean;
  columns?: Column[];
}
